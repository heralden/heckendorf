(ns clj-roguelike.components.dialog
  (:require [dumdom.core :refer [defcomponent]]
            [clj-roguelike.util :refer [get-uid]]
            [clj-roguelike.styles.dialog :refer [container title text button-group button input]]))

(defcomponent intro [$close]
  (container
    (title "WELCOME")
    (text "Welcome to the dungeon Heckendorf")
    (text "Use the arrow keys to move")
    (text "Press the alphanumeric key assigned to an item in your inventory to use it")
    (text "Find the dragon and slay it")
    (button-group
      (button {:onClick $close} "CLOSE"))))

(defcomponent copy-game [$close]
  (container
    (title "COPY GAME")
    (text "Copy the below code and save it somewhere")
    (text "You can load the game again from anywhere")
    (input {:type "text", :value (get-uid), :readonly "readonly"})
    (button-group
      (button {:onClick $close} "CLOSE"))))

(defcomponent load-game [code $input-code $load-game $close]
  (container
    (title "LOAD GAME")
    (text "Loading a game will leave the current one")
    (input {:type "text", :value code, :onChange $input-code})
    (button-group
      (button {:onClick $load-game} "LOAD")
      (button {:onClick $close} "CANCEL"))))

(defcomponent new-game [$new-game $close]
  (container
    (title "NEW GAME")
    (text "This will overwrite your current game with a fresh one")
    (text "Are you sure")
    (button-group
      (button {:onClick $new-game} "YES")
      (button {:onClick $close} "NO"))))

#_(defcomponent victory [$close]
    (container
      (title "YOU WON")
      (text "Well done")
      (text "You defeated the dragon and won the game")
      (text "Enter your name below to submit your score to the leaderboard")
      (input {:type "text"})
      (button-group
        (button "SUBMIT")
        (button {:onClick $close} "CLOSE"))))

(defcomponent victory [$new-game $close]
  (container
    (title "YOU WON")
    (text "Well done")
    (text "You defeated the dragon and won the game")
    (text "You have earned the respect of #treasureisland")
    (button-group
      (button {:onClick $new-game} "NEW GAME")
      (button {:onClick $close} "CLOSE"))))

(defcomponent death [$new-game $close]
  (container
    (title "YOU DIED")
    (text "Game over")
    (text "You failed to conquer the monsters of the dungeon")
    (text "You are free to start a new game if you dare")
    (button-group
      (button {:onClick $new-game} "NEW GAME")
      (button {:onClick $close} "CLOSE"))))

(defcomponent error []
  (container
    (title "$%&#@")
    (text "XXXXXXXXXXXXXX")))
