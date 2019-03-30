(ns heckendorf.data)

(def weapon-keys {"s" 0, "d" 1, "f" 2, "g" 3})
(def potion-keys {"c" 0, "v" 1, "b" 2})
(def hotkeys
  (into #{"h" "j" "k" "l" "n" "m" "i" "o"
          "ArrowLeft" "ArrowRight" "ArrowUp" "ArrowDown"}
        (into (keys weapon-keys) (keys potion-keys))))

(def materials
  {:common {:wood 0.6 :stone 1}
   :uncommon {:iron 2 :steel 2.2}
   :rare {:mithril 3.1 :adamant 3.8}
   :epic {:obsidian 4.5 :abyssal 5.3}})

(def grades
  "Map of all `materials` to their grades."
  (apply merge (vals materials)))

(defn inv->pots [inv]
  (->> inv
       (group-by :type)
       :potion
       (map :grade)
       frequencies
       (reduce-kv
         (fn [m k v]
           (assoc m
                  (case k :minor "c" :lesser "v" :greater "b")
                  [v k]))
         (sorted-map-by #(< (get potion-keys %1 -1) (get potion-keys %2 -1))))))

(defn inv->weps [inv]
  (->> inv
       (group-by :type)
       :weapon
       (group-by :form)
       (reduce-kv
         (fn [m k v]
           (assoc m
                  (case k :sword "s" :dagger "d" :mace "f" :greatsword "g")
                  [k (apply max-key grades (map :grade v))]))
         (sorted-map-by #(< (get weapon-keys %1 -1) (get weapon-keys %2 -1))))))

(defn hotkey->index [inv hotkey]
  (let [pot (some-> (inv->pots inv) (get hotkey))
        wep (some-> (inv->weps inv) (get hotkey))]
    (if-some [item (cond
                     (some? pot)
                     {:type :potion, :grade (second pot)}
                     (some? wep)
                     {:type :weapon, :form (first wep), :grade (second wep)})]
      (->> (map-indexed vector inv)
           (filter #(= (second %) item))
           first
           first)
      nil)))
