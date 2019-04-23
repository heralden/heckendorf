(ns heckendorf.entity
    (:require [heckendorf.random :refer [rand-range]]
              [heckendorf.dungeon :refer [rand-coord-tile neighboring-tiles edge-tile? yx->i]]))

(let [i (atom -1)]
  (defn- new-id
    "Returns a distinct numeric ID for each call."
    []
    (swap! i inc))
  (defn reset-id
    "Reset id for use when a new batch of entities is created."
    [x]
    (reset! i x)))

(def spaced-tile?
  (complement edge-tile?))

(defn simplify-keyword [keyw]
  "Returns a string of the namespace, if the keyword is namespaced; otherwise return string of keyword"
  (if (simple-keyword? keyw)
    (name keyw)
    (namespace keyw)))

(def spacious-types
  "Set of entity types that shouldn't be placed near a wall"
  #{"chest" "stair-down" "stair-up"})

(defn spacious-type? [type]
  (contains? spacious-types
             (simplify-keyword type)))

(defn unique-yx [area entity-type yxs-set]
  (let [yx (rand-coord-tile :empty area)
        unique? (not (contains? yxs-set yx))
        space? (if (spacious-type? entity-type)
                 (spaced-tile? neighboring-tiles
                               (yx->i (:width area) yx)
                               area)
                 true)]
    (if (and unique? space?)
      yx
      (recur area entity-type yxs-set))))

(defn calc-exp [lvl]
  (int (+ (* 40 lvl)
          (Math/pow (* 1.5 lvl) 2)
          (Math/pow (* 1.1 lvl) 3)
          30)))

(defn train [player exp]
  (let [{new-exp :exp, lvl :lvl, :as new-player}
        (update player :exp + exp)
        level-up? (>= new-exp (calc-exp (inc lvl)))]
    (cond-> new-player
      level-up? (-> (update :lvl inc)
                    (update :str + 2)
                    (update :max-hp + 10)
                    (update :max-stm + 2)
                    (update :message conj "You feel slightly stronger")))))

(defn regain
  ([player]
   (regain player false))
  ([player rest?]
   (update player :stm #(min (if rest? (+ % 2) (inc %))
                             (:max-stm player)))))

(defn entity-with [default-m m area entities]
  (let [new-m {:id (new-id)
               :yx (unique-yx area (:type m) (set (map :yx entities)))}]
    (conj entities (merge default-m m new-m))))

(defmulti gen-entity
  (fn [m area entities] (:type m)))

(defmethod gen-entity :default [& data]
  (apply entity-with {} data))

(defmethod gen-entity :player [& data]
  (apply entity-with
         {:max-hp 30
          :hp 30
          :max-stm 10
          :stm 10
          :str 5
          :spd 10 ; This speed is only used when the player is `unencumbered?`.
                  ; Otherwise, the speed of the equipped weapon is used.
          :exp 0
          :lvl 0
          :inventory []
          :equipped {:type :weapon, :form :fist, :grade :stone}
          :message []
          :floor 0}
         data))

(defmethod gen-entity :stair-down [& data]
  (apply entity-with {} data))

(defmethod gen-entity :stair-up [& data]
  (apply entity-with {} data))

;;;; Explanation of monster stats
;; :hp => health points
;; :atk => base damage they deal to player
;; :spd => how fast they move and attack
;; :int => how intelligent they behave
;; :vis => longest distance their line of sight can reach (does not effect espers)
;; :intangible? => whether they can walk through walls
;; :last-boss? => killing this monster will win the game

(defmethod gen-entity :monster/spider [& data]
  (apply entity-with
         {:hp (rand-range 8 12)
          :att (rand-range 1 3)
          :spd (rand-range 12 16)
          :int (rand-range 3 11)
          :vis (rand-range 3 7)}
         data))

(defmethod gen-entity :monster/skeleton [& data]
  (apply entity-with
         {:hp (rand-range 20 30)
          :att (rand-range 4 7)
          :spd (rand-range 8 11)
          :int (rand-range 5 14)
          :vis (rand-range 6 11)}
         data))

(defmethod gen-entity :monster/zombie [& data]
  (apply entity-with
         {:hp (rand-range 30 40)
          :att (rand-range 5 10)
          :spd (rand-range 5 10)
          :int (rand-range 5 14)
          :vis (rand-range 3 10)}
         data))

(defmethod gen-entity :monster/ghost [& data]
  (apply entity-with
         {:hp (rand-range 35 40)
          :att (rand-range 9 12)
          :spd (rand-range 10 18)
          :int (rand-range 8 17)
          :vis (rand-range 8 14)
          :intangible? true}
         data))

(defmethod gen-entity :monster/grim-reaper [& data]
  (apply entity-with
         {:hp (rand-range 60 80)
          :att (rand-range 13 17)
          :spd (rand-range 8 14)
          :int (rand-range 13 20)
          :vis (rand-range 12 20)
          :intangible? true}
         data))

(defmethod gen-entity :monster/drake [& data]
  (apply entity-with
         {:hp (rand-range 140 200)
          :att (rand-range 18 24)
          :spd (rand-range 8 10)
          :int (rand-range 9 13)
          :vis (rand-range 8 12)}
         data))

(defmethod gen-entity :monster/dragon [& data]
  (apply entity-with
         {:hp 1000
          :att 32
          :spd 8
          :int 15
          :vis 15
          :last-boss? true}
         data))
