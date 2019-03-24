(ns clj-roguelike.components.dialog
  (:require [dumdom.core :refer [defcomponent]]
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
    (input {:type "text"})
    (button-group
      (button "COPY")
      (button {:onClick $close} "CLOSE"))))

(defcomponent load-game [$close]
  (container
    (title "LOAD GAME")
    (text "Loading a new game will overwrite the current one")
    (input {:type "text"})
    (button-group
      (button "LOAD")
      (button {:onClick $close} "CANCEL"))))

(defcomponent new-game [$close]
  (container
    (title "NEW GAME")
    (text "This will overwrite your current game with a fresh one")
    (text "Are you sure")
    (button-group
      (button "YES")
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

(defcomponent victory [$close]
  (container
    (title "YOU WON")
    (text "Well done")
    (text "You defeated the dragon and won the game")
    (text "You have earned the respect of #treasureisland")
    (button-group
      (button "NEW GAME")
      (button {:onClick $close} "CLOSE"))))

(defcomponent death [$close]
  (container
    (title "YOU DIED")
    (text "Game over")
    (text "You failed to conquer the monsters of the dungeon")
    (text "You are free to start a new game if have not given up")
    (button-group
      (button "NEW GAME")
      (button {:onClick $close} "CLOSE"))))

(defcomponent error []
  (container
    (title "$%&#@")
    (text "XXXXXXXXXXXXXX")))
