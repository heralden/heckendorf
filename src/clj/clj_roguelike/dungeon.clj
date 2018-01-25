(ns clj-roguelike.dungeon
  (:require [clj-roguelike.random :refer [rand-range perc-chance]]))

(defn create-area [w h]
  {:width w
   :height h
   :tiles (vec (repeat (* w h) {:tile :wall}))})

(defn- yx->i [w [y x]]
  (+ x (* y w)))

(defn- i->yx [w i]
  [(int (/ i w)) (mod i w)])

(defn- next-id []
  0)

(defn- coord-range [c d]
  (map #(+ c %) (range 0 d)))

(defn- indexes-rect [w h [y x] width]
  (let [ys (coord-range y h) 
        xs (coord-range x w)]
    (for [y ys x xs] 
      (yx->i width [y x])))) 

(defn- indexes->tiles [tiles indexes]
  (map #(nth tiles % nil) indexes))

(defn- assoc-indexes [indexes tile area]
  (reduce #(assoc-in %1 [:tiles %2 :tile] tile) area indexes))

(defn- within-boundaries? [w h [y x] area]
  (and (<= (+ h y) (:height area))
       (<= (+ w x) (:width area))))

(defn- none-empty? [tiles indexes]
  (not-any? #(= :empty (:tile %)) 
            (indexes->tiles tiles indexes)))

(defn add-room [w h yx area]
  (let [indexes (indexes-rect w h yx (:width area))
        boundary (indexes-rect (+ 2 w) (+ 2 h) (mapv dec yx) (:width area))]
    (if (and (within-boundaries? w h yx area)
             (none-empty? (:tiles area) boundary))
      (assoc-indexes indexes :empty area)
      area)))

(defn- rand-coord [area]
  [(rand-int (:height area))
   (rand-int (:width area))])

(defn- random-room [area _]
  (add-room (rand-range 2 9)
            (rand-range 2 9)
            (rand-coord area)
            area))

(defn generate-dungeon [w h rms]
  (reduce random-room (create-area w h) (repeat rms nil)))

(defn pretty-print [area]
  (->> (map :tile (:tiles area))
       (map #(cond (= % :wall) :#
                   (= % :empty) :.))
       (partition (:width area))
       clojure.pprint/pprint))
