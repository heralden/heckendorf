(ns clj-roguelike.action
    (:require [clojure.string :as s]
              [clj-roguelike.entity :refer [simplify-keyword train]]
              [clj-roguelike.dungeon :refer [yx->m line-of-sight]]
              [clj-roguelike.item :refer [dmg-with gen-item potion->hp]]
              [clj-roguelike.data :refer [hotkey->index]]))

(defn yx->entity
  "Returns the entity occupying the `yx` coordinate in `game`."
  [{:keys [area entities]} yx]
  (or (first (filter #(= yx (:yx %)) entities))
      ;; Tiles aren't really entities, so let's "entitize" them.
      {:type (or (:tile (yx->m yx area)) :boundary)
       :yx yx}))

(defn vary [x]
  (Math/round (* x (+ (rand) 0.5))))

(defn random-neighbor
  "Returns the yx vector of a random neighboring coordinate of `yx`."
  [[y x]]
  (let [dirs [[(dec y) x]        ; north
              [(dec y) (inc x)]  ; north-east
              [y (inc x)]        ; east
              [(inc y) (inc x)]  ; south-east
              [(inc y) x]        ; south
              [(inc y) (dec x)]  ; south-west
              [y (dec x)]        ; west
              [(dec y) (dec x)]]]; north-west
    (rand-nth dirs)))

(defmulti encounter
  "Dispatches on entity type."
  (fn [entity-a entity-b]
    (mapv (comp keyword simplify-keyword :type)
          [entity-a entity-b])))

(defmethod encounter [:player :empty]
  [player {:keys [yx]}]
  [(assoc player :yx yx)])

(defmethod encounter [:player :wall]
  [player _]
  [player])

(defmethod encounter [:player :boundary]
  [player _]
  [player])

(defmethod encounter [:player :monster]
  [player monster]
  (let [dmg (vary (dmg-with (:str player)
                            (:spd monster)
                            (:equipped player)))
        dead? (>= dmg (:hp monster))
        monster-name (->> monster :type name)
        msg (s/join " "
                    (cond
                      dead? ["Killed" monster-name "after dealing" dmg "damage"]
                      (zero? dmg) [monster-name "dodged your attack"]
                      :else ["Dealt" dmg "damage to" monster-name]))
        new-monster (if dead?
                      {:type :dead, :id (:id monster)}
                      (-> monster
                          (update :hp - dmg)
                          (assoc :attacked? true)))
        new-player (-> player
                       (train (if dead? (:hp monster) dmg))
                       (update :message conj msg)
                       (cond-> (and dead? (:last-boss? monster))
                               (assoc :game-over :victory)))]
    [new-player new-monster]))

(defmethod encounter [:player :stair-down]
  [player _]
  [(update player :floor inc)])

(defmethod encounter [:player :stair-up]
  [player _]
  [(update player :floor dec)])

(defmethod encounter [:player :chest]
  [player chest]
  (let [items (gen-item chest)]
    [(update player :inventory into items)
     (assoc chest :type :chest-open)]))

(defmethod encounter [:monster :empty]
  [monster {:keys [yx]}]
  [(assoc monster :yx yx)])

(defmethod encounter [:monster :wall]
  [monster {:keys [yx]}]
  [(if (:intangible? monster)
     (assoc monster :yx yx)
     monster)])

(defmethod encounter [:monster :boundary]
  [monster _]
  [monster])

(defmethod encounter [:monster :player]
  [monster player]
  (let [dmg (vary (get monster :att))
        dead? (>= dmg (:hp player))
        monster-name (->> monster :type name)
        msg (s/join " "
                    (cond
                      dead? ["You died after receiving" dmg "damage from" monster-name]
                      (zero? dmg) ["You dodged the attack from" monster-name]
                      :else ["You received" dmg "damage from" monster-name]))
        new-player (if dead?
                     (-> player
                         (assoc :hp 0)
                         (update :message conj msg)
                         (assoc :game-over :death))
                     (-> player
                         (update :hp - dmg)
                         (update :message conj msg)))]
    [monster new-player]))

(defmethod encounter :default
  [entity _target]
  [entity])

;; Temporary sketch of actions
; {:type :move}
; ;; rest and attack are really subset actions of move (walk would be
; ;; a more suitable blanket term) that are initiated depending on the
; ;; target coordinate. It may make sense to integrate them?
; {:type :rest}
; {:type :attack}
; {:type :use} ; using potions or equipping items

(defn remvec [v i]
  (vec (concat (subvec v 0 i)
               (subvec v (inc i)))))

(defmulti dispatch
  (fn [game entity-id]
    (some-> game :entities (nth entity-id) :next-action :type)))

(defmethod dispatch :walk
  [{:keys [entities], :as game} entity-id]
  (let [{{:keys [dir]} :next-action, [y x] :yx, :as entity}
        (entities entity-id)
        next-yx (case (name dir)
                  "north" [(dec y) x]
                  "east"  [y (inc x)]
                  "south" [(inc y) x]
                  "west"  [y (dec x)])
        target-entity (yx->entity game next-yx)]
    (encounter entity target-entity)))

(defmethod dispatch :use
  [{:keys [entities]} entity-id]
  (let [{{:keys [hotkey]} :next-action,
         inv :inventory, :as entity} (entities entity-id)
        index (hotkey->index inv hotkey)
        item (get inv index)]
    (case (:type item)
      :potion (let [{:keys [max-hp hp]} entity
                    healed (min (potion->hp item) (- max-hp hp))
                    msg (str "You drank a potion healing you by "
                             healed
                             " points")]
                [(-> entity
                     (update :hp + healed)
                     (update :inventory remvec index)
                     (update :message conj msg))])
      :weapon (let [weapon (->> ((juxt :grade :form) item)
                                (map name)
                                (s/join " "))
                    msg (str "You equip a " weapon)]
                [(-> entity
                     (assoc :equipped item)
                     (update :message conj msg))])
      [entity])))

(defn sights-player? [game {:keys [vis yx]}]
  (let [sighted-types (->> (line-of-sight (:area game) vis yx)
                           (map (partial yx->entity game))
                           (map :type)
                           set)]
    (contains? sighted-types :player)))

(defn approach-player [game {:keys [yx] :as entity}]
  (let [[py px] (:yx (get-in game [:entities 0]))
        [my mx] yx
        target-yx (-> yx
                      (cond-> (> py my) (update 0 inc))
                      (cond-> (> px mx) (update 1 inc))
                      (cond-> (< py my) (update 0 dec))
                      (cond-> (< px mx) (update 1 dec)))
        target-entity (yx->entity game target-yx)]
    (encounter entity target-entity)))

(defn wander [game {:keys [yx] :as entity}]
  (let [random-target-entity (yx->entity game (random-neighbor yx))]
    (encounter entity (rand-nth [random-target-entity entity]))))

(defn behavior [game entity level]
  (case level
    :dumb ;; Walks and attacks randomly and aimlessly
    (wander game entity)
    :aware ;; Walks and attacks randomly, but will chase (within sight) if attacked
    (cond
      (and (:attacked? entity)
           (sights-player? game entity)) (approach-player game entity)
      :else (wander game entity))
    :normal ;; Walks randomly, but will chase when player is in sight
    (cond
      (sights-player? game entity) (approach-player game entity)
      :else (wander game entity))
    :esper ;; Always aware of player, and will chase relentlessly
    (approach-player game entity)))

;; This is the default method for non-player `tickable-entities`. Doesn't apply
;; to `player` as the game-loop only runs when their :next-action is defined.
(defmethod dispatch :default
  [{:keys [entities], :as game} entity-id]
  (let [entity (entities entity-id)
        behave (partial behavior game entity)]
    (condp >= (:int entity)
           5 (behave :dumb)
           10 (behave :aware)
           15 (behave :normal)
           20 (behave :esper)
           (behave :dumb))))

;;;; Entity ticks for initiating actions/events

;;; All living entities have a `:ticks` key in their entity map, which is
;;; based on the entity's `:spd` value. The value of `:ticks` represents the
;;; remaining amount of ticks before the entity is allowed to initiate a new
;;; action.

(def ^:const base-speed
  "All speeds are relative to this value."
  10)

(def ^:const tickable-entities
  "Entity namespaces/simple keywords that `should-tick?`."
  #{"monster" "player"})

(defn- ticks-per-action
  "Computes the amount of ticks between each action for `spd`."
  [spd]
  (-> (* base-speed base-speed)
      (/ spd)
      double
      Math/round))

(defn- should-tick?
  "Returns whether the `entity` type allows ticking."
  [entity]
  (contains? tickable-entities (simplify-keyword (:type entity))))

(defn- has-ticks?
  "Returns true if `entity` has ticks key defined."
  [entity]
  (boolean (:ticks entity)))

(defn- reset-ticks
  "Sets the initial remaining ticks to an `entity` depending on its spd key."
  [entity]
  (assoc entity :ticks (ticks-per-action (:spd entity))))

(defn- tick
  "Initiates a single tick to `entity`, resetting it once it goes below zero."
  [entity]
  (let [next-entity (update entity :ticks dec)]
    (if (neg? (:ticks next-entity))
      (reset-ticks entity)
      next-entity)))

(defn- apply-tick
  "Safely applies tick to any `entity` by first checking if it's tickable and
  resetting the amount of ticks if necessary."
  [entity]
  (cond
    (and (has-ticks? entity)
         (should-tick? entity)) (tick entity)
    (should-tick? entity) (tick (reset-ticks entity))
    :else entity))

(defn merge-dispatch [entities game id]
  "Dispatches action on an entity that has ticked, replacing all entities with
  new updated entities of the same id. First entity returned should always be
  the dispatching entity (id) so it can have its tick applied."
  (reduce (fn [es e] (assoc es (:id e) e)) ; entity id's are the same as their index
          entities
          (update (dispatch game id) 0 apply-tick)))

(defn effect-entities
  "Runs through a full game-loop. Ticks down entities and runs `merge-dispatch`
  whenever an entity reaches its turn to dispatch their action. Returns right
  before the player entity dispatches, so we can prompt for input."
  ([game] (effect-entities game 0 true))
  ([game id initial?]
   (let [entities (:entities game)
         entity   (nth entities id)
         enticks  (if (should-tick? entity) (or (:ticks entity) 0) -1)
         next-id  (if (contains? entities (inc id)) (inc id) 0)]
     (cond
       (and (not initial?) (zero? id) (zero? enticks)) game
       (zero? enticks) (recur (update game :entities merge-dispatch game id) next-id false)
       :else (recur (update-in game [:entities id] apply-tick) next-id false)))))
