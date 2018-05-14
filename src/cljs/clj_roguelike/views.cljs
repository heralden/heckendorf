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
; image-rendering: crisp-edges;
(def style-rendering
  {:image-rendering "-moz-crisp-edges"})

(defn tile-style [yx]
  (into
    style-rendering
    {:background-image "url('/images/tileset.png')"
     :background-position (get-tileset-pos yx)
     :background-size (str tileset-width "px")}))

(def empty-style
  (into
    style-rendering
    {:background-color "white"}))

(defn simplify-keyword [keyw]
  (if (simple-keyword? keyw)
    (name keyw)
    (namespace keyw)))

(defn tile->graphic [width index tile]
  ^{:key index}
  [:div {:style
         (into
           (case (simplify-keyword (:tile tile))
             "empty"      empty-style
             "wall"       (tile-style [0 3])
             "monster"    (tile-style [5 4])
             "chest"      (tile-style [7 5])
             "stair-down" (tile-style [6 7])
             "stair-up"   (tile-style [6 6])
             "player"     (tile-style [2 7])
             (tile-style [0 0]))
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
