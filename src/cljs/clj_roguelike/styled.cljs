(ns clj-roguelike.styled
  (:require [garden.units :as u]
            [herb.core :include-macros true :refer [defglobal]])
  (:require-macros [clj-roguelike.util :refer [defstyled]]))

(defglobal global
  [:body {:font-family "monospace"
          :background-color "black"
          :overflow "hidden"}])

(defstyled status-bar []
  :span
  {:font-size (u/px 16)
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

(defstyled tile [type position size bg-size basis]
  :div
  ^{:key type}
  {:width (u/px size)
   :height (u/px size)
   :flex-basis (u/percent basis)
   :image-rendering #{"-moz-crisp-edges"
                      "-webkit-crisp-edges"
                      "pixelated"
                      "crisp-edges"}
   :background-image "url('/images/tileset.png')"
   :background-position position
   :background-size (u/px bg-size)})

(defstyled game-over-container []
  :div
  {:position "absolute"
   :top (u/percent 50)
   :left (u/percent 50)
   :transform "translate(-50%, -50%)"})

(defstyled game-over-message []
  :h1
  {:font-size (u/px 72)
   :opacity 0.65
   :color "red"})
