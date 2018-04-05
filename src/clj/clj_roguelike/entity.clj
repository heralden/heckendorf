(ns clj-roguelike.entity
    (:require [clj-roguelike.random :refer [rand-range]]
              [clj-roguelike.dungeon :refer [i->yx]]))

; (defmulti entity :type)

; (defmethod entity :monster/skeleton [m]
;   (into m {}))

; (defmethod entity :monster/spider)
; (defmethod entity :monster/zombie)
; (defmethod entity :monster/ghost)
; (defmethod entity :monster/grim-reaper)
; (defmethod entity :monster/dragon)

; (entity {:type :monster/skeleton})

(defn rand-coord-tile [tile area]
  (let [i (rand-int (* (:width area) (:height area)))]
    (if (= (:tile (nth (:tiles area) i))
           tile)
      (i->yx (:width area) i)
      (recur tile area))))
