(ns clj-roguelike.action)

;;;; Using event sourcing to interact with game state

;; Temporary sketch of actions
; {:type :move}
; ;; rest and attack are really subset actions of move (walk would be
; ;; a more suitable blanket term) that are initiated depending on the
; ;; target coordinate. It may make sense to integrate them?
; {:type :rest}
; {:type :attack}
; {:type :use} ; using potions or equipping items

(defmulti dispatch
  (fn [action _entity] (:type action)))

;; TODO: When an action is dispatched, it will be added in queue
;; as next-action in the player entity. All entities are ticked
;; down until it's finally executed. (done in `game->web`) Then
;; the new game is sent to the client.

(defmethod dispatch :walk
  [{:keys [dir]} entity]
  (update entity
          :yx
          (fn [[y x]]
              (case (name dir)
                "north" [(dec y) x]
                "east"  [y (inc x)]
                "south" [(inc y) x]
                "west"  [y (dec x)]))))

#_(defn- valid-action? [action])

#_(defn- effect [state action])

#_(defn apply-effect [state action])

#_(defn effect-all [state actions]
  (reduce apply-effect state actions))

;;;; Entity ticks for initiating actions/events

;;; All living entities have a `:ticks` key in their entity map, which is
;;; based on the entity's `:spd` value. The value of `:ticks` represents the
;;; remaining amount of ticks before the entity is allowed to initiate a new
;;; action.

(def ^:const base-speed
  "All speeds are relative to this value."
  10)

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
  (= (namespace (:type entity)) "monster"))

(defn- reset-ticks
  "Sets the initial remaining ticks to an `entity` depending on its spd."
  [entity]
  (if (should-tick? entity)
    (assoc entity :ticks (ticks-per-action (:spd entity)))
    entity))

(defn reset-ticks-all
  "`reset-ticks` on a sequence of `entities`."
  [entities]
  (map reset-ticks entities))

(defn- has-ticks?
  "Returns true if `entity` has ticks key defined."
  [entity]
  (boolean (:ticks entity)))

(defn- tick
  "Initiates a single tick to `entity`."
  [entity]
  (if (should-tick? entity)
    (let [next-entity (update entity :ticks dec)]
      (if (zero? (:ticks next-entity))
        (reset-ticks entity) ; also initiate action here
        next-entity))
    entity))

(defn- apply-tick
  "Safely applies `tick` to `entity`, resetting the key if not defined."
  [entity]
  (if (has-ticks? entity)
    (tick entity)
    (tick (reset-ticks entity))))

(defn tick-all
  "`apply-tick` to all `entities`."
  [entities]
  (map apply-tick entities))
