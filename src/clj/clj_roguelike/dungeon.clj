(ns clj-roguelike.dungeon)

(defn create-area [w h]
  {:width w
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

(defn add-room [w h yx area]
  (let [indexes (indexes-rect w h yx (:width area))]
    (if (every? #(= :wall (:tile %)) (map (partial nth (:tiles area)) indexes))
      (reduce #(assoc-in %1 [:tiles %2 :tile] :empty) area indexes)
      area)))
