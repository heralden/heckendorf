(ns clj-roguelike.dungeon
  (:require [clj-roguelike.random :refer [rand-range perc-chance]]))

(def current-id (atom 0))
(defn new-id []
  (swap! current-id inc))

(defn create-area [w h]
  {:width w
   :height h
   :tiles (vec (repeat (* w h) {:tile :wall}))})

(defn- yx->i [w [y x]]
  (if (< -1 x w)
    (+ x (* y w))
    -1))

(defn- i->yx [w i]
  [(int (/ i w)) (mod i w)])

(defn- i->tile [index area]
  (:tile (nth (:tiles area) index nil)))

(defn- coord-range [c d]
  (map #(+ c %) (range 0 d)))

(defn- indexes-rect [w h [y x] width]
  (let [ys (coord-range y h) 
        xs (coord-range x w)]
    (for [y ys x xs] 
      (yx->i width [y x])))) 

(defn- indexes->tiles [tiles indexes]
  (map #(nth tiles % nil) indexes))

(defn- assoc-room [id area index]
  (-> area
      (assoc-in [:tiles index :tile] :empty)
      (assoc-in [:tiles index :id] id)))

(defn- assoc-room-indexes [indexes id area]
  (reduce (partial assoc-room id) area indexes))

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
      (assoc-room-indexes indexes (new-id) area)
      area)))

(defn- rand-coord [area]
  [(rand-int (:height area))
   (rand-int (:width area))])

(defn- random-room [area _]
  (add-room (rand-range 2 9)
            (rand-range 2 9)
            (rand-coord area)
            area))

(defn- adjacent-tiles [index area]
  (let [w (:width area)
        [y x] (i->yx w index)]
    (->> [[(dec y) x]     ; North
          [y (inc x)]     ; East
          [(inc y) x]     ; South
          [y (dec x)]]    ; West 
         (map (partial yx->i w))
         (filter (complement neg?))))) 

(defn- edge-tile? [index area]
  (let [tile (i->tile index area)]
    (and (= tile :empty)
         (some #(= (i->tile % area) :wall)
               (adjacent-tiles index area)))))

(defn- edge-tiles [area]
  (keep-indexed (fn [index _] (when (edge-tile? index area) index))
                (:tiles area)))

#_(defn- random-corridors [area]
  (edge-tiles area))

(defn generate-dungeon [w h room-attempts]
  (reduce random-room (create-area w h) (repeat room-attempts nil)))

(defn pretty-print [area]
  (->> (map :tile (:tiles area))
       (map #(cond (= % :wall) :#
                   (= % :empty) :.))
       (partition (:width area))
       clojure.pprint/pprint))
