(ns clj-roguelike.ui
  (:require [clojure.string :as s]
            [dumdom.core :refer [defcomponent]]
            [clj-roguelike.util :as util]
            [clj-roguelike.styled :as styled]))

(def real-tileset-width 128)
(def real-tile-size 16)
(def tile-scale 2)

(def tileset-width (* tile-scale real-tileset-width))
(def tile-size (* tile-scale real-tile-size))

(defn tile->type [tile]
  (if (= (namespace tile) "chest")
    (namespace tile)
    (name tile)))

(defn type->coord [type]
  (case type
    "empty"       [8 7]
    "wall"        [0 3]
    "dark"        [3 3]
    "chest"       [7 5]
    "chest-open"  [7 6]
    "stair-down"  [6 7]
    "stair-up"    [6 6]
    "player"      [8 0]
    "spider"      [8 1]
    "skeleton"    [8 2]
    "zombie"      [8 3]
    "ghost"       [8 4]
    "grim-reaper" [8 5]
    "drake"       [8 6]
    "dragon"      [5 4]
    [0 0]))

(defn coord->pos [[y x]]
  (let [offset-tile-size (fn [n] (+ (* n tile-size) tile-size))]
    (str (if (pos? y)
           (str "bottom " (offset-tile-size y) "px ")
           "top 0px ")
         (if (pos? x)
           (str "right " (offset-tile-size x) "px")
           "left 0px"))))

(defn tile->pos [tile]
  (-> tile tile->type type->coord coord->pos))

(defcomponent game-tile [{:keys [tile game-over]} index width]
  (styled/tile {:key index
                :type (tile->type tile)
                :position (case game-over
                            :death (coord->pos [2 7])
                            (tile->pos tile))
                :size tile-size
                :bg-size tileset-width
                :basis (* (/ 1 width) 100)}))

(defcomponent game-tiles [{:keys [tiles width]}]
  (styled/area
    {:width (* tile-size width)}
    (map game-tile
         tiles
         (range)
         (repeat width))))

(defn item->str [item]
  (case (:type item)
    :weapon ((juxt :grade :form) item)
    :potion ((juxt :grade :type) item)))

(defcomponent player-info
  [{:keys [hp max-hp exp lvl equipped inventory message floor game-over] :as player}]
  [:div
   (when game-over
     (styled/game-over-container
       (styled/game-over-message
         (case game-over
           :victory "YOU WON"
           :death "YOU DIED"
           "GAME OVER"))))
   (styled/status-bar
     (if (some? player)
       (s/join " "
               ["HP" (str hp \/ max-hp)
                "XP" (int exp)
                "LVL" lvl
                "EQP" (cond-> equipped
                              (map? equipped) item->str)
                "INV" (->> (map item->str inventory)
                           (zipmap util/hotkeys))
                "FLR" (-> floor inc -)])
       "LOADING"))
   (when (not-empty message)
     (styled/message-log
       (for [[index text] (map-indexed vector (take-last 5 message))]
            (styled/log-entry
              {:key index}
              (s/upper-case text)))))])

(defcomponent main-panel [{:keys [game]}]
  [:div
   (player-info (:player game))
   (game-tiles game)])
