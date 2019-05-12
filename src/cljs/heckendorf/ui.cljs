(ns heckendorf.ui
  (:require [clojure.string :as s]
            [dumdom.core :refer [defcomponent]]
            [heckendorf.data :refer [inv->pots inv->weps item->vec]]
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

(defcomponent game-tile [{:keys [tile seen? game-over]} index width]
  (styled/tile {:key index
                :type (tile->type tile)
                :position (case game-over
                            :death (coord->pos [2 7])
                            (tile->pos tile))
                :size tile-size
                :bg-size tileset-width
                :basis (* (/ 1 width) 100)
                :darkened? seen?}))

(defcomponent game-tiles [{:keys [tiles width]}]
  (styled/area
    {:width (* tile-size width)}
    (map game-tile
         tiles
         (range)
         (repeat width))))

(defcomponent game-interface [state $m]
  (let [{{:keys [player]} :game, dialog :dialog, leaders :leaderboard,
         {:keys [code name]} :input, res :res, offline? :offline?} state
        {:keys [hp max-hp stm max-stm exp lvl equipped inventory message floor
                game-over actions]} player
        {:keys [$close $open-intro $open-copy $open-load $open-new $open-leaderboard
                $new-game $load-game $submit-name $input-code $input-name]} $m]

    [:div
     (case dialog
       :intro (dialog/intro $close)
       :copy (dialog/copy-game $close)
       :load (dialog/load-game code $input-code $load-game $close)
       :new (dialog/new-game $new-game $close)
       :leaderboard (dialog/leaderboard {:res (res dialog)
                                         :leaders leaders}
                                        $close)
       :game-over (case game-over
                    :victory (dialog/victory {:name name
                                              :actions actions
                                              :res (res game-over)}
                                             $input-name
                                             $submit-name
                                             $close)
                    :death (dialog/death actions $new-game $close)
                    nil)
       nil)

     (when offline? (dialog/offline))

     [:div

      (if (some? player)
        [:div
         (styled/status-bar
           (s/join " "
                   ["HP" (str hp \/ max-hp)
                    "STM" (str stm \/ max-stm)
                    "XP" (int exp)
                    "LVL" (inc lvl)
                    "EQP" (if (= (:form equipped) :fist)
                            :none
                            (item->vec equipped))
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
        (styled/button {:onClick $open-new} "NEW GAME")
        (styled/button {:onClick $open-leaderboard} "LEADERBOARD"))]

     (when (not-empty message)
       (styled/message-log
         (for [[index text] (map-indexed vector (take-last 10 message))]
              (styled/log-entry
                {:key index}
                (s/upper-case text)))))]))

(defcomponent main-panel [state $m]
  [:div
   (game-interface state $m)
   (game-tiles (:game state))])
