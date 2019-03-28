(ns heckendorf.item
  (:require [heckendorf.random :refer [rand-range perc-vec]]
            [heckendorf.data :refer [materials grades]]))

(def weapons
  {:fist       {:att 1 :spd 9}
   :dagger     {:att 3 :spd 9}
   :sword      {:att 5 :spd 6}
   :mace       {:att 8 :spd 4}
   :greatsword {:att 18 :spd 2}})

(defn- truncate-decimals
  ([x]
   (truncate-decimals x 1))
  ([x amount]
   (let [multiplier (Math/pow 10 amount)]
     (float (/ (Math/round (* x multiplier))
               multiplier)))))

(defn- calc-dmg [str-multiplier grade-multiplier enemy-spd wep]
  (let [{:keys [att spd]} wep
        chance-to-hit (* (/ spd enemy-spd) 2)
        final-att (+ (/ att 10) 1)
        dmg (* grade-multiplier str-multiplier final-att)
        jitter (* dmg 0.2)
        real-dmg (+ (* (rand) jitter) (- dmg (/ jitter 2)))]
    (if (< (rand) chance-to-hit)
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
