(ns clj-roguelike.action
    (:require [clojure.string :as s]
              [clj-roguelike.entity :refer [simplify-keyword]]
              [clj-roguelike.dungeon :refer [yx->m]]
              [clj-roguelike.item :refer [dmg-with]]))

(defn yx->entity
  "Returns the entity occupying the `yx` coordinate in `game`."
  [{:keys [area entities]} yx]
  (or (first (filter #(= yx (:yx %)) entities))
      ;; Tiles aren't really entities, so let's "entitize" them.
      {:type (:tile (yx->m yx area)) :yx yx}))

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

(defmethod encounter [:player :monster]
  [player monster]
  (let [dmg (dmg-with (:str player)
                      (:spd monster)
                      (:equipped player))
        msg (s/join " "
                    ["Did" dmg "damage to" (->> monster :type name)])]
    [(assoc player :message msg)
     (update monster :hp - dmg)]))

(defmethod encounter [:monster :empty]
  [monster {:keys [yx]}]
  [(assoc monster :yx yx)])

(defmethod encounter [:monster :wall]
  [monster _]
  [monster])

(defmethod encounter :default
  [entity _]
  [entity])

;; Temporary sketch of actions
; {:type :move}
; ;; rest and attack are really subset actions of move (walk would be
; ;; a more suitable blanket term) that are initiated depending on the
; ;; target coordinate. It may make sense to integrate them?
; {:type :rest}
; {:type :attack}
; {:type :use} ; using potions or equipping items

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

;; This is the default method for non-player `tickable-entities`. Doesn't apply
;; to `player` as the game-loop only runs when their :next-action is defined.
(defmethod dispatch :default
  [{:keys [entities], :as game} entity-id]
  (let [entity (entities entity-id)
        next-yx (random-neighbor (get entity :yx))
        temp-target-entity (yx->entity game next-yx)]
    (encounter entity (rand-nth [temp-target-entity entity]))))
;; TODO proper enemy behaviour

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
