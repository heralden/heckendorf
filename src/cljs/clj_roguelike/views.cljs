(ns clj-roguelike.views
  (:require [re-frame.core :as re-frame]
            [clj-roguelike.subs :as subs]
            ))

(def ^:const tile-size 16)

(defn tile->graphic [width index tile]
  ^{:key index} 
  [:div {:style {:background-image "url('/images/tileset.png')"
                 :width (str tile-size "px")
                 :height (str tile-size "px")
                 :flex-basis (str (* (/ 1 width) 100) "%")}}])

(defn game-tiles []
  (let [{:keys [tiles width]} @(re-frame/subscribe [::subs/game-state])]
    [:div {:style {:display "flex", 
                   :flex-wrap "wrap", 
                   :width (* tile-size width)}}
     (map-indexed
       (partial tile->graphic width)
       tiles)]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div "Hello from " @name (game-tiles)]))
