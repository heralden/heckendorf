(ns clj-roguelike.item
    (:require [clj-roguelike.random :refer [rand-range perc-vec]]))

(def material
  {:common {:wood 0.6 :stone 1}
   :uncommon {:iron 2 :steel 2.2}
   :rare {:mithril 3.1 :adamant 3.8}
   :epic {:obsidian 4.5 :abyssal 5.3}})

(def weapon
  {:dagger {:att 3 :spd 8}
   :mace {:att 6 :spd 4}
   :sword {:att 5 :spd 5}
   :greatsword {:att 14 :spd 2}})

(def potion
  {:minor #(rand-range 10 20)
   :lesser #(rand-range 40 60)
   :greater #(rand-range 160 250)})

(defn rand-weapon [type]
  {:type :weapon
   :form (-> weapon
             keys
             rand-nth)
   :grade (-> (type material)
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
             [50 #(rand-weapon :epic)]
             [80 #(rand-weapon :rare)]]))
