(ns heckendorf.components.dialog
  (:require [dumdom.core :refer [defcomponent]]
            [heckendorf.util :refer [get-uid]]
            [heckendorf.styles.dialog :refer [container title text small-text text-group text-part button-group button input]]))

(defcomponent offline []
  (container
    (title "LOST CONNECTION TO SERVER")
    (text "This dialog will disappear once connection is re-established")))

(defcomponent intro [$close]
  (container
    (title "INTRO")
    (text "Welcome to the dungeon Heckendorf")
    (text "Use the arrow or numpad keys or h j k l n m i o to move")
    (text "Press two arrow keys at the same time for diagonal movement")
    (text "Hold shift while moving to consume your stamina and dash")
    (text "Press R to rest in place and regain stamina faster")
    (text "Press the key assigned to an item in your inventory to use it")
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

(defcomponent victory [{:keys [name actions res]} $input-name $submit-name $close]
  (container
    (title "YOU WON")
    (text "Well done")
    (text "You defeated the dragon and won the game")
    (text "You have earned the respect of #treasureisland")
    (text (str "You spent " actions " actions in this game"))
    (text "Enter your name below to submit your score to the leaderboard")
    (input {:type "text", :value name, :onChange $input-name})
    (when (some? res) (text res))
    (button-group
      (button {:onClick $submit-name} "SUBMIT")
      (button {:onClick $close} "CLOSE"))))

(defcomponent leaderboard [{:keys [res leaders]} $close-res]
  (container
    (title "LEADERBOARD")
    (when (some? res) (text res))
    (if (empty? leaders)
      [:div
       (text "The leaderboard is empty")
       (text "Perhaps you should change that")]
      (text-group
        (for [part (partition 20 leaders)]
          (text-part
            (for [score part]
              (small-text (prn-str score)))))))
    (button-group
      (button {:onClick $close-res} "CLOSE"))))

(def hints
  ["Monsters get stronger as you descend but treasures get better"
   "You can go back up a floor to get more monsters to train on"
   "Dashing into monsters from a distance will do more damage"
   "Heavier weapon forms will make you move and attack slower"
   "Dealing damage will help you level up making you stronger"
   "Dashing without enough stamina will make you exhausted"
   "Potions will not heal you above your max hitpoints"])

(defcomponent death [actions $new-game $close]
  (container
    (title "YOU DIED")
    (text "Game over")
    (text "You failed to conquer the monsters of the dungeon")
    (text (str "You spent " actions " actions in this game"))
    (text "HINT")
    (text (rand-nth hints))
    (button-group
      (button {:onClick $new-game} "NEW GAME")
      (button {:onClick $close} "CLOSE"))))

(defcomponent error []
  (container
    (title "$%&#@")
    (text "XXXXXXXXXXXXXX")))
