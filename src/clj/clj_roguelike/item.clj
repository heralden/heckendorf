(ns clj-roguelike.item
    (:require [clj-roguelike.random :refer [rand-range perc-vec]]))

(def materials
  {:common {:wood 0.6 :stone 1}
   :uncommon {:iron 2 :steel 2.2}
   :rare {:mithril 3.1 :adamant 3.8}
   :epic {:obsidian 4.5 :abyssal 5.3}})

(def grades
  "Map of all `materials` to their grades."
  (apply merge (vals materials)))

(def weapons
  {:fist       {:att 1 :spd 9}
   :dagger     {:att 3 :spd 9}
   :sword      {:att 5 :spd 6}
   :mace       {:att 8 :spd 4}
   :greatsword {:att 18 :spd 2}})

(defn- calc-dmg [str-multiplier grade-multiplier enemy-spd wep]
  (let [{:keys [att spd]} wep
        chance-to-hit (* (/ spd enemy-spd) 2)
        final-att (+ (/ att 10) 1)]
    (if (< (rand) chance-to-hit)
      (float (* grade-multiplier str-multiplier final-att))
      0)))

(defn dmg-with
  "Calculates damage using strength, speed of enemy and weapon item."
  [strength enemy-spd {:keys [form grade]}]
  (let [str-multiplier (+ (/ strength 10) 1)
        grade-multiplier (get grades grade)
        weapon (get weapons form)]
    (calc-dmg str-multiplier grade-multiplier enemy-spd weapon)))

(def potion
  {:minor #(rand-range 10 20)
   :lesser #(rand-range 40 60)
   :greater #(rand-range 160 250)})

(defn rand-weapon [type]
  {:type :weapon
   :form (-> weapons
             (dissoc :fist)
             keys
             rand-nth)
   :grade (-> (type materials)
              keys
              rand-nth)})

(defn rand-potion [grade]
  {:type :potion
   :grade grade})

(defmulti gen-item :type)

(defmethod gen-item :chest/common [_]
  (perc-vec [[50 #(rand-potion :minor)]
             [20 #(rand-potion :minor)]
             [70 #(rand-weapon :common)]]))

(defmethod gen-item :chest/uncommon [_]
  (perc-vec [[80 #(rand-potion :minor)]
             [40 #(rand-potion :lesser)]
             [70 #(rand-weapon :uncommon)]]))

(defmethod gen-item :chest/rare [_]
  (perc-vec [[70 #(rand-potion :lesser)]
             [40 #(rand-potion :greater)]
             [60 #(rand-weapon :rare)]
             [70 #(rand-weapon :uncommon)]]))

(defmethod gen-item :chest/epic [_]
  (perc-vec [[60 #(rand-potion :greater)]
             [50 #(rand-potion :greater)]
             [50 #(rand-potion :lesser)]
             [60 #(rand-weapon :epic)]
             [80 #(rand-weapon :rare)]]))
