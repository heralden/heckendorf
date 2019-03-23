(ns clj-roguelike.dungeon
  (:require [clj-roguelike.random :refer [rand-range perc-chance]]))

(def ^:const additional-tunnel-perc 10)
(def ^:const min-room-length 2)
(def ^:const max-room-length 9)

(let [i (atom 0)]
  (defn- new-id
    "Returns a distinct numeric ID for each call."
    []
    (swap! i inc)))

(defn create-area [w h tile]
  {:width w
   :height h
   :tiles (vec (repeat (* w h) {:tile tile}))})

(defn i->yx [w i]
  [(int (/ i w)) (mod i w)])

(defn i->m [index area]
  (nth (:tiles area) index nil))

(defn yx->i [w [y x]]
  (if (< -1 x w)
    (+ x (* y w))
    -1))

(defn yx->m [yx area]
  (i->m (yx->i (:width area) yx) area))

(defn- empty-tile? [area index]
  (= :empty (:tile (i->m index area))))

(defn- wall-tile? [area index]
  (= :wall (:tile (i->m index area))))

(defn out-of-bounds? [w h i]
  "Checks whether an index is invalid for the area width and height"
  (or (neg? i)
      (>= i (* w h))))

(defn get-wh [area]
  "Returns a vector [width height] of area"
  ((juxt :width :height) area))

(defn solid-yx? [area yx]
  "Checks whether a coordinate is a wall tile or out of bounds"
  (let [[w h] (get-wh area)]
    (or (= (:tile (yx->m yx area)) :wall)
        (out-of-bounds? w h (yx->i w yx)))))

(defn- take-nums [start amount]
  (map (partial + start) (range 0 amount)))

(defn- indexes-rect [w h [y x] area-width]
  (let [ys (take-nums y h)
        xs (take-nums x w)]
    (for [y ys x xs]
      (yx->i area-width [y x]))))

(defn- carve-tile [id area index]
  (-> area
      (assoc-in [:tiles index :tile] :empty)
      (assoc-in [:tiles index :id] id)))

(defn- carve-tile-indexes [indexes id area]
  (reduce (partial carve-tile id) area indexes))

(defn- within-boundaries? [w h [y x] area]
  (and (< 0 y (+ h y) (:height area))
       (< 0 x (+ w x) (:width area))))

(defn add-room [w h yx area]
  (let [indexes (indexes-rect w h yx (:width area))
        boundary-idxs (indexes-rect (+ 2 w) (+ 2 h) (mapv dec yx) (:width area))]
    (if (and (within-boundaries? w h yx area)
             (not-any? (partial empty-tile? area) boundary-idxs))
      (carve-tile-indexes indexes (new-id) area)
      area)))

(defn- rand-coord [area]
  [(rand-int (:height area))
   (rand-int (:width area))])

(defn- random-room [area _]
  (add-room (rand-range min-room-length max-room-length)
            (rand-range min-room-length max-room-length)
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

(defn neighboring-tiles [index area]
  (let [w (:width area)
        [y x] (i->yx w index)]
    (->> [[(dec y) x]        ; north
          [(dec y) (inc x)]  ; north-east
          [y (inc x)]        ; east
          [(inc y) (inc x)]  ; south-east
          [(inc y) x]        ; south
          [(inc y) (dec x)]  ; south-west
          [y (dec x)]        ; west
          [(dec y) (dec x)]] ; north-west
         (map (partial yx->i w))
         (filter (complement neg?)))))

(defn edge-tile? [tile-f index area]
  (and (empty-tile? area index)
       (some (partial wall-tile? area)
             (tile-f index area))))

(defn- edge-tiles [area]
  (keep-indexed (fn [index _]
                    (when (edge-tile? adjacent-tiles index area)
                      index))
                (:tiles area)))

(defn- corner-tile? [index area]
  (= 2 (->> (map (partial wall-tile? area)
                 (adjacent-tiles index area))
            (filter true?)
            count)))

(defn- adjacent-walls [index area]
  (filter (partial wall-tile? area)
          (adjacent-tiles index area)))

(defn- walk-until [pred [w h] yxs dir]
  (let [next-yx (mapv + (first yxs) dir)
        next-i (yx->i w next-yx)]
    (cond
      (out-of-bounds? w h next-i) nil
      (pred next-i) (cons next-yx yxs)
      :else (recur pred [w h] (cons next-yx yxs) dir))))

(defn- trace-corridors [index area]
  (let [w (:width area)
        yx (i->yx w index)]
    (->> area
         (adjacent-walls index)
         (map (partial i->yx w))
         (map #(mapv - %2 %1) (repeat yx))
         (map (partial walk-until
                       (partial empty-tile? area)
                       [w (:height area)]
                       [yx])))))

(defn- same-id? [yx1 yx2 area]
  (= (:id (yx->m yx1 area))
     (:id (yx->m yx2 area))))

(defn- swap-id [old new area]
  (assoc area
         :tiles
         (mapv #(if (= old (:id %))
                 (assoc % :id new)
                 %)
              (:tiles area))))

(defn- create-corridor [area yxs]
  (let [ayx (first yxs)
        byx (last yxs)
        old-id (:id (yx->m ayx area))
        id (:id (yx->m byx area))
        indexes (map (partial yx->i (:width area))
                     (drop-last (rest yxs)))]
    (if (same-id? ayx byx area)
      (if (perc-chance additional-tunnel-perc)
        (carve-tile-indexes indexes id area)
        area)
      (->> area
           (carve-tile-indexes indexes id)
           (swap-id old-id id)))))

(defn- create-corridors [area index]
  (let [lyxs (filter seq? (trace-corridors index area))]
    (reduce create-corridor area lyxs)))

(defn- connected-dungeon? [area]
  (if-let [ids (seq (->> area
                         :tiles
                         (map :id)
                         (filter number?)))]
    (apply = ids)
    false))

(defn- carved-dungeon? [area]
  (boolean (seq (->> area
                     :tiles
                     (map :tile)
                     (filter #{:empty})))))

(defn- valid-dungeon? [area]
  (and (connected-dungeon? area)
       (carved-dungeon? area)))

(defn- generate-rooms [room-attempts area]
  (reduce random-room area (repeat room-attempts nil)))

(defn- generate-corridors [area]
  (->> area
       edge-tiles
       shuffle
       (reduce create-corridors area)))

(defn generate-dungeon [w h room-attempts]
  (let [dung (->> (create-area w h :wall)
                  (generate-rooms room-attempts)
                  (generate-corridors))]
    (if (valid-dungeon? dung)
      dung
      (recur w h room-attempts))))

(defn pretty-print [area]
  (->> (map :tile (:tiles area))
       (map #(cond (= % :wall)  :#
                   (= % :empty) :.
                   (= % :dark)  :X))
       (partition (:width area))
       clojure.pprint/pprint))

(defn rand-coord-tile [tile area]
  (let [i (rand-int (* (:width area) (:height area)))]
    (if (= (:tile (nth (:tiles area) i))
           tile)
      (i->yx (:width area) i)
      (recur tile area))))

;; Auxiliary functions for working with coordinates

(defn- add-y [[y x] n] [(+ y n) x])
(defn- add-x [[y x] n] [y (+ x n)])
(defn- sub-y [[y x] n] [(- y n) x])
(defn- sub-x [[y x] n] [y (- x n)])

(defn- pos-line-y [len [y x]]
  (map (fn [rising-y] [rising-y x])
       (range y (+ y len))))
(defn- pos-line-x [len [y x]]
  (map (fn [rising-x] [y rising-x])
       (range x (+ x len))))
(defn- neg-line-y [len [y x]]
  (map (fn [falling-y] [falling-y x])
       (range y (- y len) -1)))
(defn- neg-line-x [len [y x]]
  (map (fn [falling-x] [y falling-x])
       (range x (- x len) -1)))

(defn eighth-pivot [n]
  "Creates a list of vectors of numbers from origo to n distance,
  which will create a gradual 45 degree pivot from origo when
  applied to coordinates"
  (reductions #(update %1 %2 inc)
              (vec (repeat n 0))
              (mapcat #(-> % (range n) reverse)
                      (range 1 n))))

(defn apply-pivot-yx
  "Takes multiple functions to apply pivot-f to a line from origo-yx"
  [pivot-f line-f add-coord-f len origo-yx]
  (mapv (partial mapv add-coord-f)
        (repeat (line-f len origo-yx))
        (pivot-f len)))

(defn complete-pivot [len origo-yx]
  "Creates nested vectors of a complete 360 degree pivot.
  Every other 45 degree section will have its last path vector
  popped, as it will overlap with the previous section."
  (let [apply-eighth-pivot (partial apply-pivot-yx eighth-pivot)]
    (concat
      (apply-eighth-pivot neg-line-y add-x len origo-yx)
      (pop (apply-eighth-pivot pos-line-x sub-y len origo-yx))
      (apply-eighth-pivot pos-line-x add-y len origo-yx)
      (pop (apply-eighth-pivot pos-line-y add-x len origo-yx))
      (apply-eighth-pivot pos-line-y sub-x len origo-yx)
      (pop (apply-eighth-pivot neg-line-x add-y len origo-yx))
      (apply-eighth-pivot neg-line-x sub-y len origo-yx)
      (pop (apply-eighth-pivot neg-line-y sub-x len origo-yx)))))

(defn trace-path-until [pred yxs]
  "Walks through the yxs vector of coordinates until either
  exhausting it or until pred returns true for a tile map. It
  will then return a vector of the elements up to and including
  that element."
  (reduce (fn [acc yx]
              (let [next-acc (conj acc yx)]
                (if (pred yx)
                  (reduced next-acc)
                  next-acc)))
          [] yxs))

(defn prune-paths [pred paths]
  "Uses trace-path-until to prune all paths according to predicate."
  (mapv (partial trace-path-until pred)
        paths))

(defn line-of-sight [area len origo-yx]
  "Returns a set of coordinates that are within the line of sight
  of origo-yx with a range of len in area"
  (->> (complete-pivot len origo-yx)
       (prune-paths (partial solid-yx? area))
       (apply concat)
       set))

(defn apply-tile-idx [tile area idx]
  "Apply the tile to the specified index in area"
  (assoc-in area [:tiles idx] tile))

#_(defn apply-tile-idxs [tile area idxs]
    "Apply the tile to a vector of indexes in area"
    (reduce (partial apply-tile-idx tile) area idxs))

(defn copy-tile-idx [from-area to-area idx]
  "Copy a tile at index from one area to another"
  (let [tile (i->m idx from-area)]
    (apply-tile-idx tile to-area idx)))

(defn darken-other-idxs [area los-idxs]
  "Create a new area with all tiles except los-idxs replaced by :dark tiles"
  (let [[w h] (get-wh area)]
    (reduce (partial copy-tile-idx area)
            (create-area w h :dark)
            los-idxs)))

(defn apply-los-darken [area los-yxs]
  "Apply los-yxs returned by line-of-sight to area"
  (let [[w _] (get-wh area)
        idxs (map (partial yx->i w) los-yxs)]
    (darken-other-idxs area idxs)))

(defn darken-dungeon [area len origo-yx]
  "Return area with :dark tiles applied outside line of sight"
  (apply-los-darken
    area
    (line-of-sight area len origo-yx)))
