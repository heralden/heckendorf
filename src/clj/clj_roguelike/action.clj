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

(defn- reset-ticks
  "Sets the initial remaining ticks to an `entity` depending on its spd."
  [entity]
  (let [ticks (ticks-per-action (:spd entity))]
    (assoc entity :ticks ticks)))

(defn reset-ticks-all
  "`reset-ticks` on a sequence of `entities`."
  [entities]
  (map reset-ticks entities))

(defn- valid-entity?
  "Returns true if `entity` is tickable."
  [entity]
  (boolean (:ticks entity)))

(defn- tick 
  "Initiates a single tick to `entity`."
  [entity]
  (let [next-entity (update entity :ticks dec)]
    (if (zero? (:ticks next-entity))
      (reset-ticks entity) ; also initiate action here
      next-entity)))

(defn- apply-tick
  "Safely applies `tick` to `entity`, resetting the key if not defined."
  [entity]
  (if (valid-entity? entity)
    (tick entity)
    (tick (reset-ticks entity))))

(defn tick-all
  "`apply-tick` to all `entities`."
  [entities]
  (map apply-tick entities))
