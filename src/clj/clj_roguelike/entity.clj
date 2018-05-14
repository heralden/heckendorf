(ns clj-roguelike.entity
    (:require [clj-roguelike.random :refer [rand-range]]
              [clj-roguelike.dungeon :refer [rand-coord-tile]]))

(defn unique-yx [area yxs-set]
  (let [yx (rand-coord-tile :empty area)]
    (if (contains? yxs-set yx)
      (recur area yxs-set)
      yx)))

(defn entity-with [props m area entities]
  (->> props
       (into {:yx (unique-yx area (set (map :yx entities)))})
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
          :exp 0
          :lvl 1
          :inventory []
          :equipped :none}
         data))

(defmethod gen-entity :stair-down [& data]
  (apply entity-with {} data))

(defmethod gen-entity :stair-up [& data]
  (apply entity-with {} data))

(defmethod gen-entity :monster/spider [& data]
  (apply entity-with
         {:hp (rand-range 6 10)
          :att (rand-range 2 5)}
         data))

(defmethod gen-entity :monster/skeleton [& data]
  (apply entity-with
         {:hp (rand-range 15 25)
          :att (rand-range 4 7)}
         data))

(defmethod gen-entity :monster/zombie [& data]
  (apply entity-with
         {:hp (rand-range 20 30)
          :att (rand-range 5 10)}
         data))

(defmethod gen-entity :monster/ghost [& data]
  (apply entity-with
         {:hp (rand-range 50 65)
          :att (rand-range 15 20)}
         data))

(defmethod gen-entity :monster/grim-reaper [& data]
  (apply entity-with
         {:hp (rand-range 100 126)
          :att (rand-range 30 50)}
         data))

(defmethod gen-entity :monster/drake [& data]
  (apply entity-with
         {:hp (rand-range 310 350)
          :att (rand-range 40 60)}
         data))

(defmethod gen-entity :monster/dragon [& data]
  (apply entity-with
         {:hp (rand-range 1000 1100)
          :att (rand-range 70 110)}
         data))
