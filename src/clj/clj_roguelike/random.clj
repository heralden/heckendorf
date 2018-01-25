(ns clj-roguelike.random)

(defn rand-range [min max]
  (+ min (rand-int (- max min))))

(defn perc-chance [perc]
  (< (rand) (/ perc 100)))
