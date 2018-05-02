(ns clj-roguelike.game-test
    (:require [clj-roguelike.game :refer :all]
              [midje.sweet :refer :all]))

(def game {:area
           {:width 4,
            :height 4,
            :tiles [{:tile :wall} {:tile :wall} {:tile :wall} {:tile :wall}
                    {:tile :wall} {:tile :empty} {:tile :empty} {:tile :wall}
                    {:tile :wall} {:tile :empty} {:tile :empty} {:tile :wall}
                    {:tile :wall} {:tile :wall} {:tile :wall} {:tile :wall}]}
           :entities [{:type :monster/spider, :yx [1 1], :hp 7, :att 2}
                      {:type :monster/spider, :yx [2 1], :hp 9, :att 2}
                      {:type :monster/spider, :yx [1 2], :hp 8, :att 3}
                      {:type :monster/spider, :yx [2 2], :hp 8, :att 2}]})

(fact "It will normalize entity types into the correct tiles"
  (normalize-game game) => {:width 4, 
                            :height 4, 
                            :tiles [{:tile :wall} {:tile :wall} {:tile :wall} {:tile :wall}
                                    {:tile :wall} {:tile :monster/spider} {:tile :monster/spider} {:tile :wall}
                                    {:tile :wall} {:tile :monster/spider} {:tile :monster/spider} {:tile :wall}
                                    {:tile :wall} {:tile :wall} {:tile :wall} {:tile :wall}]})
