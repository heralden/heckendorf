(ns clj-roguelike.views
    (:require [re-frame.core :as re-frame]
              [clj-roguelike.subs :as subs]
              ))

(def real-tileset-width 128)
(def real-tile-size 16)
(def tile-scale 2)

(def tileset-width (* tile-scale real-tileset-width))
(def tile-size (* tile-scale real-tile-size))

(defn get-tileset-pos [[y x]]
  (let [offset-tile-size (fn [n] (+ (* n tile-size) tile-size))]
    (str (if (pos? y)
           (str "bottom " (offset-tile-size y) "px ")
           "top 0px ")
         (if (pos? x)
           (str "right " (offset-tile-size x) "px")
           "left 0px"))))

; TODO find out how to specify multiple values for one key
; What we want here is:
; image-rendering: -moz-crisp-edges;
; image-rendering: -webkit-crisp-edges;
; image-rendering: pixelated;
; image-rendering: crisp-edges;<Paste>
(def style-rendering 
  {:image-rendering "-moz-crisp-edges"})

(def wall-style 
  (into 
    style-rendering
    {:background-image "url('/images/tileset.png')"
     :background-position (get-tileset-pos [0 3])
     :background-size (str tileset-width "px")}))

(def empty-style 
  (into
    style-rendering
    {:background-color "white"}))

(defn tile->graphic [width index tile]
  ^{:key index} 
  [:div {:style 
         (into
           (if (= (:tile tile) :wall)
             wall-style
             empty-style)
           {:width (str tile-size "px")
            :height (str tile-size "px")
            :flex-basis (str (* (/ 1 width) 100) "%")})}])

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
