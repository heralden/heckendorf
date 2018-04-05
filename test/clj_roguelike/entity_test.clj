(ns clj-roguelike.entity-test
    (:require [clj-roguelike.dungeon :refer [generate-dungeon yx->m]]
              [clj-roguelike.entity :refer [rand-coord-tile]]
              [midje.sweet :refer :all]))

(def dung (generate-dungeon 25 25 100))

(fact "Returns coord of random tile that matches"
  (->> (repeatedly 50 #(rand-coord-tile :empty dung))
       (map #(yx->m % dung))
       (map :tile)) => (take 50 (repeat :empty)))
