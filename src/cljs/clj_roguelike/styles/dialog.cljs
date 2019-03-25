(ns clj-roguelike.styles.dialog
  (:require [garden.units :as u]
            [clj-roguelike.styles.core :refer [retro-font retro-border absolute-center]])
  (:require-macros [clj-roguelike.util :refer [defstyled]]))

(defstyled container []
  :div
  (merge
    absolute-center
    {:border retro-border
     :padding [[(u/px 8) (u/px 12)]]
     :background-color 'black}))

(defstyled title []
  :h4
  {:position 'absolute
   :top (u/px -24)
   :left (u/percent 50)
   :transform "translate(-50%, -50%)"
   :font retro-font
   :border retro-border
   :color 'white
   :background-color 'black
   :padding [[(u/px 2) (u/px 4)]]})

(defstyled text []
  :p
  {:font retro-font
   :text-transform 'uppercase
   :color "white"})

(defstyled input []
  :input
  {:font retro-font
   :border retro-border
   :width (u/percent 100)
   :box-sizing 'border-box})

(defstyled button-group []
  :div
  {:margin-top (u/px 15)
   :margin-bottom (u/px 5)
   :display 'flex
   :justify-content 'center})

(defstyled button []
  :button
  ^{:pseudo {:focus {:text-decoration {:color 'white
                                       :style 'solid
                                       :line 'underline}
                     :outline 0}}}
  {:font retro-font
   :border retro-border
   :color 'white
   :background-color 'black
   :margin [[(u/px 0) (u/px 8)]]
   :width (u/px 110)})
