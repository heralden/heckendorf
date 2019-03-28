(ns heckendorf.random)

(defn rand-range [min max]
  "Returns a number between min (inclusive) and max (inclusive)"
  (+ min (rand-int (- (inc max) min))))

(defn perc-chance [perc]
  "Has a perc percent chance of returning true instead of false"
  (< (rand) (/ perc 100)))

(defn perc-vec [v]
  "Maps through a vector of pairs, where the first element is the percentage chance for returning the second element"
  (->> v
       (mapv #(let [perc (first %)
                    fun (second %)]
                (if (perc-chance perc)
                  (fun)
                  nil)))
       (filterv (complement nil?))))
