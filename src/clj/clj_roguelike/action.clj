(ns clj-roguelike.action
    (:require [clj-roguelike.entity :refer [simplify-keyword]]))

;; Temporary sketch of actions
; {:type :move}
; ;; rest and attack are really subset actions of move (walk would be
; ;; a more suitable blanket term) that are initiated depending on the
; ;; target coordinate. It may make sense to integrate them?
; {:type :rest}
; {:type :attack}
; {:type :use} ; using potions or equipping items

(defmulti dispatch
  (fn [entity] (-> entity :next-action :type)))

(defmethod dispatch :walk
  [{{:keys [dir]} :next-action, :as entity}]
  (update entity
          :yx
          (fn [[y x]]
              (case (name dir)
                "north" [(dec y) x]
                "east"  [y (inc x)]
                "south" [(inc y) x]
                "west"  [y (dec x)]))))

(declare tick-all)
(declare ticks-per-action)

(defn effect-all [game]
  (update-in game
             [:entities]
             (fn [[player :as entities]]
                 (loop [ticks-until (ticks-per-action (:spd player))
                        entities entities]
                   (if (zero? ticks-until)
                     entities
                     (recur (dec ticks-until)
                            (tick-all entities)))))))

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

(defn- reset-ticks
  "Sets the initial remaining ticks to an `entity` depending on its spd."
  [entity]
  (if (should-tick? entity)
    (assoc entity :ticks (ticks-per-action (:spd entity)))
    entity))

(defn reset-ticks-all
  "`reset-ticks` on a sequence of `entities`."
  [entities]
  (mapv reset-ticks entities))

(defn- has-ticks?
  "Returns true if `entity` has ticks key defined."
  [entity]
  (boolean (:ticks entity)))

(defn- tick
  "Initiates a single tick to `entity`."
  [entity]
  (if (should-tick? entity)
    (let [next-entity (update entity :ticks dec)]
      (if (and (zero? (:ticks next-entity))
               (zero? (:id next-entity)))
        ;; It's the player.
        (reset-ticks (dispatch entity))
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
  (mapv apply-tick entities))
