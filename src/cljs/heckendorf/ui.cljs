(ns heckendorf.ui
  (:require [clojure.string :as s]
            [dumdom.core :refer [defcomponent]]
            [heckendorf.data :refer [inv->pots inv->weps]]
            [heckendorf.styles.core :as styled]
            [heckendorf.components.dialog :as dialog]))

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

(defcomponent game-interface [state $m]
  (let [{{:keys [player]} :game, dialog :dialog, {:keys [code]} :input} state
        {:keys [hp max-hp exp lvl equipped inventory message floor game-over]} player
        {:keys [$close $open-intro $open-copy $open-load $open-new $new-game
                $load-game $input-code]} $m]

    [:div

     (case dialog
       :intro (dialog/intro $close)
       :copy (dialog/copy-game $close)
       :load (dialog/load-game code $input-code $load-game $close)
       :new (dialog/new-game $new-game $close)
       :game-over (case game-over
                    :victory (dialog/victory $new-game $close)
                    :death (dialog/death $new-game $close)
                    nil)
       nil)

     [:div

      (if (some? player)
        [:div
         (styled/status-bar
           (s/join " "
                   ["HP" (str hp \/ max-hp)
                    "XP" (int exp)
                    "LVL" (inc lvl)
                    "EQP" (cond-> equipped
                                  (map? equipped) item->str)
                    "FLR" (-> floor inc -)]))
         (styled/status-bar
           (str "POTIONS " (inv->pots inventory)))
         (styled/status-bar
           (str "WEAPONS " (inv->weps inventory)))]
        "LOADING")

      (styled/menu
        (styled/button {:onClick $open-intro} "INTRO")
        (styled/button {:onClick $open-copy} "COPY GAME")
        (styled/button {:onClick $open-load} "LOAD GAME")
        (styled/button {:onClick $open-new} "NEW GAME"))]

     (when (not-empty message)
       (styled/message-log
         (for [[index text] (map-indexed vector (take-last 5 message))]
              (styled/log-entry
                {:key index}
                (s/upper-case text)))))]))

(defcomponent main-panel [state $m]
  [:div
   (game-interface state $m)
   (game-tiles (:game state))])
