(ns heckendorf.action
    (:require [clojure.string :as s]
              [heckendorf.entity :refer [simplify-keyword train regain]]
              [heckendorf.dungeon :refer [yx->m line-of-sight]]
              [heckendorf.item :refer [dmg-with gen-item potion->hp weapons]]
              [heckendorf.data :refer [hotkey->index bounded-conj]]))

(def ^:const dash-cost 10)

(defn yx->entity
  "Returns the entity occupying the `yx` coordinate in `game`."
  [{:keys [area entities]} yx]
  (or (first (filter #(= yx (:yx %)) entities))
      ;; Tiles aren't really entities, so let's "entitize" them.
      {:type (or (:tile (yx->m yx area)) :boundary)
       :yx yx}))

(defn vary [x]
  (Math/round (* x (+ (rand) 0.5))))

(defn distance [ayx byx]
  (let [[ay ax] ayx
        [by bx] byx]
    (+ (Math/abs (- ay by))
       (Math/abs (- ax bx)))))

(defn neighbors [[y x]]
  [[(dec y) x]         ; north
   [(dec y) (inc x)]   ; north-east
   [y (inc x)]         ; east
   [(inc y) (inc x)]   ; south-east
   [(inc y) x]         ; south
   [(inc y) (dec x)]   ; south-west
   [y (dec x)]         ; west
   [(dec y) (dec x)]]) ; north-west

(defn random-neighbor
  "Returns the yx vector of a random neighboring coordinate of `yx`."
  [yx]
  (rand-nth (neighbors yx)))

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
  (let [dash? (:dash? player)
        dmg (* (vary (dmg-with (:str player)
                               (:spd monster)
                               (:equipped player)))
               (if dash? 2 1))
        dead? (>= dmg (:hp monster))
        monster-name (->> monster :type name)
        msg (->> (cond
                   dead? ["Killed" monster-name "after"
                          (when dash? "charging and")
                          "dealing" dmg "damage"]
                   (zero? dmg) [monster-name "dodged your"
                                (if dash? "charge" "attack")]
                   :else [(if dash? "Charged and dealt" "Dealt") dmg "damage to" monster-name])
                 (filter some?)
                 (s/join " "))
        new-monster (if dead?
                      {:type :dead, :id (:id monster)}
                      (-> monster
                          (update :hp - dmg)
                          (assoc :attacked? true)))
        new-player (-> player
                       (dissoc :dash?)
                       (train (if dead? (:hp monster) dmg))
                       (update :message bounded-conj msg)
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
                         (update :message bounded-conj msg)
                         (assoc :game-over :death))
                     (-> player
                         (update :hp - dmg)
                         (update :message bounded-conj msg)))]
    [monster new-player]))

(defmethod encounter :default
  [entity _target]
  [entity])

(defn remvec [v i]
  (vec (concat (subvec v 0 i)
               (subvec v (inc i)))))

(defmulti dispatch
  (fn [game entity-id]
    (some-> game :entities (nth entity-id) :next-action :type)))

(defn monster? [entity]
  (= (-> entity :type namespace) "monster"))

(defmethod dispatch :walk
  [{:keys [entities], :as game} entity-id]
  (let [{{:keys [dir]} :next-action, [y x] :yx, :as entity}
        (entities entity-id)
        next-yx (case dir
                  :north [(dec y) x]
                  :north-east [(dec y) (inc x)]
                  :east  [y (inc x)]
                  :south-east [(inc y) (inc x)]
                  :south [(inc y) x]
                  :south-west [(inc y) (dec x)]
                  :west  [y (dec x)]
                  :north-west [(dec y) (dec x)])
        target-entity (yx->entity game next-yx)]
    (if (monster? target-entity)
      (encounter entity target-entity)
      (encounter (regain entity) target-entity))))

(defmethod dispatch :dash
  [{:keys [entities], :as game} entity-id]
  (let [{{:keys [dir]} :next-action, [y x] :yx, :as entity}
        (entities entity-id)
        next-yx (case dir
                  :north [(dec y) x]
                  :north-east [(dec y) (inc x)]
                  :east  [y (inc x)]
                  :south-east [(inc y) (inc x)]
                  :south [(inc y) x]
                  :south-west [(inc y) (dec x)]
                  :west  [y (dec x)]
                  :north-west [(dec y) (dec x)])
        target-yx (case dir
                    :north [(- y 2) x]
                    :north-east [(- y 2) (+ x 2)]
                    :east  [y (+ x 2)]
                    :south-east [(+ y 2) (+ x 2)]
                    :south [(+ y 2) x]
                    :south-west [(+ y 2) (- x 2)]
                    :west  [y (- x 2)]
                    :north-west [(- y 2) (- x 2)])
        next-entity (yx->entity game next-yx)
        target-entity (yx->entity game target-yx)
        exhausted? (< (:stm entity) dash-cost)
        entity (update entity :stm #(if exhausted? 0 (- % dash-cost)))]
    (cond
      exhausted? (encounter
                   (update entity :message bounded-conj "You are exhausted and failed to dash")
                   next-entity)
      (and (= (:type next-entity) :empty)
           (= (:type target-entity) :empty)) (encounter entity target-entity)
      (and (= (:type next-entity) :empty)) (encounter (-> entity
                                                          (assoc :yx next-yx)
                                                          (cond-> (monster? target-entity)
                                                                  (assoc :dash? true)))
                                                      target-entity)
      :else (encounter entity next-entity))))

(defmethod dispatch :use
  [{:keys [entities]} entity-id]
  (let [{{:keys [hotkey]} :next-action,
         inv :inventory, :as entity} (entities entity-id)
        index (hotkey->index inv hotkey)
        item (get inv index)
        entity (regain entity)]
    (case (:type item)
      :potion (let [{:keys [max-hp hp]} entity
                    healed (min (potion->hp item) (- max-hp hp))
                    msg (str "You drank a potion healing you by "
                             healed
                             " points")]
                [(-> entity
                     (update :hp + healed)
                     (update :inventory remvec index)
                     (assoc :unencumbered? true)
                     (update :message bounded-conj msg))])
      :weapon (let [weapon (->> ((juxt :grade :form) item)
                                (map name)
                                (s/join " "))
                    msg (str "You equip a " weapon)]
                [(-> entity
                     (assoc :equipped item)
                     (update :message bounded-conj msg))])
      [entity])))

(defmethod dispatch :rest
  [{:keys [entities]} entity-id]
  [(-> (entities entity-id)
       (regain true)
       (assoc :unencumbered? true))])

(defn sights-player? [game {:keys [vis yx]}]
  (let [sighted-types (->> (line-of-sight (:area game) vis yx)
                           (map (partial yx->entity game))
                           (map :type)
                           set)]
    (contains? sighted-types :player)))

(defn seen-player? [{:keys [last-seen]}]
  (some? last-seen))

(defn approach
  "Returns the yx of the traversable tile that is closest to `target-yx`."
  [game target-yx entity-yx intangible?]
  (if-some [next-yx (some->> (neighbors entity-yx)
                             (map (partial yx->entity game))
                             (filter #(or intangible? (not= (:type %) :wall)))
                             (map :yx)
                             (map #(vector (distance % target-yx) %))
                             (apply min-key first)
                             second)]
    next-yx
    entity-yx))

(defn approach-last-seen [game {:keys [yx last-seen intangible?] :as entity}]
  (let [target-yx (approach game last-seen yx intangible?)
        new-entity (cond-> entity (= target-yx last-seen) (dissoc :last-seen))
        target-entity (yx->entity game target-yx)]
    (encounter new-entity target-entity)))

(defn approach-player [game {:keys [yx intangible?] :as entity}]
  (let [player-yx (get-in game [:entities 0 :yx])
        target-yx (approach game player-yx yx intangible?)
        new-entity (assoc entity :last-seen player-yx)
        target-entity (yx->entity game target-yx)]
    (encounter new-entity target-entity)))

(defn wander [game {:keys [yx intangible?] :as entity}]
  (let [random-target-entity (->> (neighbors yx)
                                  (map (partial yx->entity game))
                                  (filter #(or intangible? (not= (:type %) :wall)))
                                  rand-nth)]
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
      (seen-player? entity) (approach-last-seen game entity)
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
  "Sets the initial remaining ticks to an `entity`."
  [entity]
  (let [spd (if (and (= (:type entity) :player)
                     (not (:unencumbered? entity)))
              (-> entity :equipped :form weapons :spd)
              (:spd entity))]
    (-> entity
        (cond-> (:unencumbered? entity) (dissoc :unencumbered?))
        (assoc :ticks (ticks-per-action spd)))))

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
