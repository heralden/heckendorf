(ns clj-roguelike.entity
    (:require [clj-roguelike.random :refer [rand-range]]
              [clj-roguelike.dungeon :refer [rand-coord-tile neighboring-tiles edge-tile? yx->i]]))

(let [i (atom -1)]
  (defn- new-id
    "Returns a distinct numeric ID for each call."
    []
    (swap! i inc))
  (defn reset-id
    "Reset id for use when a new batch of entities is created."
    [x]
    (reset! i x)))

(def spaced-tile?
  (complement edge-tile?))

(defn simplify-keyword [keyw]
  "Returns a string of the namespace, if the keyword is namespaced; otherwise return string of keyword"
  (if (simple-keyword? keyw)
    (name keyw)
    (namespace keyw)))

(def spacious-types
  "Set of entity types that shouldn't be placed near a wall"
  #{"chest" "stair-down" "stair-up"})

(defn spacious-type? [type]
  (contains? spacious-types
             (simplify-keyword type)))

(defn unique-yx [area entity-type yxs-set]
  (let [yx (rand-coord-tile :empty area)
        unique? (not (contains? yxs-set yx))
        space? (if (spacious-type? entity-type)
                 (spaced-tile? neighboring-tiles
                               (yx->i (:width area) yx)
                               area)
                 true)]
    (if (and unique? space?)
      yx
      (recur area entity-type yxs-set))))

(defn entity-with [props m area entities]
  (->> props
       (into {:id (new-id)
              :yx (unique-yx area
                             (:type m)
                             (set (map :yx entities)))})
       (into m)
       (conj entities)))

(defmulti gen-entity
  (fn [m area entities] (:type m)))

(defmethod gen-entity :default [& data]
  (apply entity-with {} data))

(defmethod gen-entity :player [& data]
  (apply entity-with
         {:hp 30
          :str 5 ; dmg = (str / wep.spd) + wep.att
          :spd 10
          :exp 0
          :lvl 1
          :inventory []
          :equipped :none
          :message ""}
         data))

(defmethod gen-entity :stair-down [& data]
  (apply entity-with {} data))

(defmethod gen-entity :stair-up [& data]
  (apply entity-with {} data))

;; Estimated optimal weapons for spd
;; 1-7: greatsword
;; 7-11: mace
;; 11-16: sword
;; 16+: dagger

(defmethod gen-entity :monster/spider [& data]
  (apply entity-with
         {:hp (rand-range 6 10)
          :att (rand-range 2 5)
          :spd (rand-range 14 20)}
         data))

(defmethod gen-entity :monster/skeleton [& data]
  (apply entity-with
         {:hp (rand-range 15 25)
          :att (rand-range 4 7)
          :spd (rand-range 8 11)}
         data))

(defmethod gen-entity :monster/zombie [& data]
  (apply entity-with
         {:hp (rand-range 20 30)
          :att (rand-range 5 12)
          :spd (rand-range 5 10)}
         data))

(defmethod gen-entity :monster/ghost [& data]
  (apply entity-with
         {:hp (rand-range 50 65)
          :att (rand-range 15 20)
          :spd (rand-range 10 20)}
         data))

(defmethod gen-entity :monster/grim-reaper [& data]
  (apply entity-with
         {:hp (rand-range 100 126)
          :att (rand-range 30 50)
          :spd (rand-range 8 14)}
         data))

(defmethod gen-entity :monster/drake [& data]
  (apply entity-with
         {:hp (rand-range 310 350)
          :att (rand-range 40 60)
          :spd (rand-range 6 9)}
         data))

(defmethod gen-entity :monster/dragon [& data]
  (apply entity-with
         {:hp (rand-range 1000 1100)
          :att (rand-range 70 110)
          :spd (rand-range 5 7)}
         data))
