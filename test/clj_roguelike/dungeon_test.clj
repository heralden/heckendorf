(ns clj-roguelike.dungeon-test
  (:require [clj-roguelike.dungeon :refer :all]
            [midje.sweet :refer :all]))

(fact "Creates an area"
  (->> (create-area 10 10)
       :tiles
       (map :tile)) => (repeat 100 :wall))

(fact "Adds room to an area"
  (->> (create-area 4 4)
       (add-room 2 2 [0 0])
       :tiles
       (mapv :tile)) => [:empty :empty :wall  :wall
                         :empty :empty :wall  :wall
                         :wall  :wall  :wall  :wall
                         :wall  :wall  :wall  :wall])

(fact "Does not add overlapping rooms"
  (->> (create-area 4 4)
       (add-room 2 2 [0 0])
       (add-room 2 2 [1 1])
       :tiles
       (mapv :tile)) => [:empty :empty :wall  :wall
                         :empty :empty :wall  :wall
                         :wall  :wall  :wall  :wall
                         :wall  :wall  :wall  :wall])

(fact "Adds additional rooms"
  (->> (create-area 4 4)
       (add-room 2 2 [0 0])
       (add-room 2 2 [2 2])
       :tiles
       (mapv :tile)) => [:empty :empty :wall  :wall
                         :empty :empty :wall  :wall
                         :wall  :wall  :empty :empty
                         :wall  :wall  :empty :empty])
