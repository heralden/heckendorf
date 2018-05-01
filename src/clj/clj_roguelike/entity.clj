(ns clj-roguelike.entity
    (:require [clj-roguelike.random :refer [rand-range]]
              [clj-roguelike.dungeon :refer [rand-coord-tile]]))

(defmulti gen-entity
  (fn [m _ _] (:type m)))

(defmethod gen-entity :default [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)})))

(defmethod gen-entity :player [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp 30
                 :str 5 ; dmg = (str / wep.spd) + wep.att
                 :exp 0
                 :lvl 1
                 :inventory []
                 :equipped :none})))

(defmethod gen-entity :monster/spider [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp (rand-range 6 10)
                 :att (rand-range 2 5)})))

(defmethod gen-entity :monster/skeleton [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp (rand-range 15 25)
                 :att (rand-range 4 7)})))

(defmethod gen-entity :monster/zombie [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp (rand-range 20 30)
                 :att (rand-range 5 10)})))

(defmethod gen-entity :monster/ghost [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp (rand-range 50 65)
                 :att (rand-range 15 20)})))

(defmethod gen-entity :monster/grim-reaper [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp (rand-range 100 126)
                 :att (rand-range 30 50)})))

(defmethod gen-entity :monster/drake [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp (rand-range 310 350)
                 :att (rand-range 40 60)})))

(defmethod gen-entity :monster/dragon [m area entities]
  (conj entities
        (into m {:yx (rand-coord-tile :empty area)
                 :hp (rand-range 1000 1100)
                 :att (rand-range 70 110)})))
