(ns clj-roguelike.entity-test
    (:require [clj-roguelike.dungeon :refer [generate-dungeon yx->m rand-coord-tile]]
              [clj-roguelike.entity :refer :all]
              [midje.sweet :refer :all]))

(def dung (generate-dungeon 25 25 100))

(fact "Returns coord of random tile that matches"
  (->> (repeatedly 50 #(rand-coord-tile :empty dung))
       (map #(yx->m % dung))
       (map :tile)) => (take 50 (repeat :empty)))

(fact "It will always assign a unique coordinate to entities"
  (let [entity-yxs (map :yx
                        (nth (iterate
                               (partial gen-entity {:type :monster/spider} dung)
                               []) 100))]
    (count (set entity-yxs)) => (count entity-yxs)))
