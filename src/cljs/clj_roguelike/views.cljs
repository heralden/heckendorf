(ns clj-roguelike.views
  (:require [re-frame.core :as re-frame]
            [clj-roguelike.subs :as subs]
            ))

(defn tile->graphic [width index tile]
  ^{:key index} [:div {:style {:background-color
                               (if (= (:tile tile) :wall)
                                 "brown"
                                 "gray")
                               :width "20px"
                               :height "20px"
                               :flex-basis (str (* (/ 1 width) 100) "%")}}])

(defn tiles [game]
  (let [tiles (:tiles @game)
        width (:width @game)]
    (map-indexed
      (partial tile->graphic width)
      tiles)))

(defn game-tiles []
  (let [game (re-frame/subscribe [::subs/game-state])]
    [:div {:style {:display "flex", :flex-wrap "wrap"}} (tiles game)]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div "Hello from " @name (game-tiles)]))
