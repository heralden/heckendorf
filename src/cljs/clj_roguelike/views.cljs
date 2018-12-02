(ns clj-roguelike.views
    (:require [re-frame.core :as re-frame]
              [clj-roguelike.subs :as subs]
              [clojure.string :as s]))

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

(defn tile->graphic [width index {:keys [tile]}]
  ^{:key index}
  [:div {:style
         (into
           (case (if (= (namespace tile) "chest")
                   (namespace tile)
                   (name tile))
             "empty"       empty-style
             "wall"        (tile-style [0 3])
             "dark"        (tile-style [3 3])
             "chest"       (tile-style [7 5])
             "stair-down"  (tile-style [6 7])
             "stair-up"    (tile-style [6 6])
             "player"      (tile-style [8 0])
             "spider"      (tile-style [8 1])
             "skeleton"    (tile-style [8 2])
             "zombie"      (tile-style [8 3])
             "ghost"       (tile-style [8 4])
             "grim-reaper" (tile-style [8 5])
             "drake"       (tile-style [8 6])
             "dragon"      (tile-style [5 4])
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

(defn player-info []
  (let [{{:keys [hp exp lvl equipped inventory]} :player}
        @(re-frame/subscribe [::subs/game-state])]
    [:span {:style {:font-family "monospace"
                    :font-size "16px"
                    :color "white"}}
     (s/join " "
             ["HP" hp
              "XP" exp
              "LVL" lvl
              "EQP" equipped
              "INV" inventory]) ]))

(defn main-panel []
  [:div (player-info) (game-tiles)])
