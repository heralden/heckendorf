(ns heckendorf.item
  (:require [heckendorf.random :refer [rand-range perc-vec]]
            [heckendorf.data :refer [materials grades]]))

(def weapons
  {:fist       {:att 1 :spd 10}
   :dagger     {:att 3 :spd 12}
   :sword      {:att 7 :spd 9}
   :mace       {:att 8 :spd 8}
   :greatsword {:att 12 :spd 6}})

(defn- truncate-decimals
  ([x]
   (truncate-decimals x 1))
  ([x amount]
   (let [multiplier (Math/pow 10 amount)]
     (float (/ (Math/round (* x multiplier))
               multiplier)))))

(defn- calc-dmg
  "We use the weapon spd to determine the chance of hitting. To calculate the
  damage, we use the weapon grade, weapon form att and the player's strength."
  [str-multiplier grade-multiplier enemy-spd wep]
  (let [{:keys [att spd]} wep
        chance-to-miss (* 4 (/ enemy-spd spd 100))
        final-att (+ (/ att 10) 1)
        dmg (* grade-multiplier str-multiplier final-att)
        jitter (* dmg 0.2)
        real-dmg (+ (* (rand) jitter) (- dmg (/ jitter 2)))]
    (if (> (rand) chance-to-miss)
      (truncate-decimals real-dmg)
      0)))

(defn dmg-with
  "Calculates damage using strength, speed of enemy and weapon item."
  [strength enemy-spd {:keys [form grade] :as wep}]
  (let [str-multiplier (+ (/ strength 10) 1)
        grade-multiplier (get grades grade)
        weapon (get weapons form)]
    (if (= wep :none)
      (calc-dmg str-multiplier 1 enemy-spd (get weapons :fist))
      (calc-dmg str-multiplier grade-multiplier enemy-spd weapon))))

(def potion
  {:minor #(rand-range 20 30)
   :lesser #(rand-range 40 60)
   :greater #(rand-range 180 250)})

(defn potion->hp [{:keys [grade type]}]
  (assert (= type :potion))
  ((potion grade)))

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
  (perc-vec [[80 #(rand-potion :minor)]
             [50 #(rand-potion :minor)]
             [60 #(rand-weapon :common)]]))

(defmethod gen-item :chest/uncommon [_]
  (perc-vec [[90 #(rand-potion :minor)]
             [40 #(rand-potion :minor)]
             [50 #(rand-potion :lesser)]
             [60 #(rand-weapon :uncommon)]]))

(defmethod gen-item :chest/rare [_]
  (perc-vec [[80 #(rand-potion :lesser)]
             [60 #(rand-potion :greater)]
             [70 #(rand-potion :minor)]
             [60 #(rand-weapon :rare)]]))

(defmethod gen-item :chest/epic [_]
  (perc-vec [[80 #(rand-potion :greater)]
             [60 #(rand-potion :lesser)]
             [50 #(rand-potion :minor)]
             [60 #(rand-weapon :epic)]
             [80 #(rand-weapon :rare)]]))
