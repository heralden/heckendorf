(ns heckendorf.styles.core
  (:require [garden.units :as u]
            [herb.core :include-macros true :refer [defglobal]])
  (:require-macros [heckendorf.util :refer [defstyled]]))

(defglobal global
  [:body {:font-family "monospace"
          :background-color "black"
          :overflow "hidden"}])

(def retro-font
  {:family "monospace"
   :weight 600
   :size (u/px 16)})

(def retro-border
  {:width (u/px 4)
   :color "white"
   :style "solid"
   :radius (u/px 3)})

(def absolute-center
  {:position 'absolute
   :top (u/percent 50)
   :left (u/percent 50)
   :transform "translate(-50%, -50%)"})

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
   :margin [[(u/px 2) (u/px 6)]]})

(defstyled menu []
  :div
  {:position 'absolute
   :top (u/px 8)
   :right (u/px 5)})

(defstyled status-bar []
  :p
  {:margin [[(u/px 2) 0]]
   :font-size (u/px 16)
   :color "white"})

(defstyled message-log []
  :div
  {:position "absolute"
   :background-color "rgba(0,0,0,0.5)"
   :padding "4px 12px"})

(defstyled log-entry []
  :p
  {:font-size (u/px 12)
   :color "white"})

(defstyled area [width]
  :div
  {:display "flex"
   :flex-wrap "wrap"
   :width (u/px width)})

(defstyled tile [type position size bg-size basis darkened?]
  :div
  ^{:key (str type (when darkened? "-seen"))}
  {:width (u/px size)
   :height (u/px size)
   :flex-basis (u/percent basis)
   :opacity (if darkened? 0.3 1.0)
   :z-index -1
   :image-rendering #{"-moz-crisp-edges"
                      "-webkit-crisp-edges"
                      "pixelated"
                      "crisp-edges"}
   :background-image "url('/images/tileset.png')"
   :background-position position
   :background-size (u/px bg-size)})
