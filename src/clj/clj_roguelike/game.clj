(ns clj-roguelike.game
    (:require [clj-roguelike.entity :refer [gen-entity]]
              [clj-roguelike.dungeon :refer [generate-dungeon darken-dungeon yx->i]]
              [clj-roguelike.random :refer [rand-range]]))

(def ^:const area-width 30)
(def ^:const area-height 30)
(def ^:const room-attempts 50)
(def ^:const sight-range 15)

(defn- rvec [v]
  "Reverses, and returns a vector"
  (vec (rseq (vec v))))

(defn- mapconj-pre [mapvec elem]
  "Map conj elem to mapvec for all items except last"
  (conj (mapv conj (pop mapvec) (repeat elem))
        (peek mapvec)))

(defn- mapconj-post [mapvec elem]
  "Map conj elem to mapvec for all items except first"
  (rvec (mapconj-pre (rvec mapvec) elem)))

(defn- add-stairs-down [floors]
  (mapconj-pre floors [#(identity 1) :stair-down]))

(defn- add-stairs-up [floors]
  (mapconj-post floors [#(identity 1) :stair-up]))

(def floors
  (-> [[[#(rand-range 8 14) :monster/spider]
        [#(rand-range 0 3)  :monster/skeleton]
        [#(rand-range 2 5)  :chest/common]
        [#(rand-range 0 2)  :chest/uncommon]]
       [[#(rand-range 6 10) :monster/spider]
        [#(rand-range 4 7)  :monster/skeleton]
        [#(rand-range 0 2)  :monster/zombie]
        [#(rand-range 1 3)  :chest/common]
        [#(rand-range 2 4)  :chest/uncommon]]
       [[#(rand-range 6 10) :monster/spider]
        [#(rand-range 8 14) :monster/skeleton]
        [#(rand-range 4 8)  :monster/zombie]
        [#(rand-range 1 4)  :monster/ghost]
        [#(rand-range 0 3)  :chest/common]
        [#(rand-range 3 5)  :chest/uncommon]]
       [[#(rand-range 3 6)  :monster/spider]
        [#(rand-range 6 10) :monster/skeleton]
        [#(rand-range 8 15) :monster/zombie]
        [#(rand-range 3 8)  :monster/ghost]
        [#(rand-range 0 3)  :monster/grim-reaper]
        [#(rand-range 1 3)  :chest/common]
        [#(rand-range 2 4)  :chest/uncommon]
        [#(rand-range 1 3)  :chest/rare]
        [#(identity 1)      :chest/epic]]
       [[#(rand-range 2 6)  :monster/spider]
        [#(rand-range 3 8)  :monster/skeleton]
        [#(rand-range 6 10) :monster/zombie]
        [#(rand-range 5 8)  :monster/ghost]
        [#(rand-range 2 6)  :monster/grim-reaper]
        [#(identity 2)      :monster/drake]
        [#(rand-range 1 3)  :chest/common]
        [#(rand-range 2 4)  :chest/uncommon]
        [#(rand-range 1 3)  :chest/rare]
        [#(identity 2)      :chest/epic]]
       [[#(rand-range 3 5)  :monster/skeleton]
        [#(rand-range 3 6)  :monster/zombie]
        [#(rand-range 2 5)  :monster/ghost]
        [#(rand-range 2 4)  :monster/grim-reaper]
        [#(identity 1)      :monster/dragon]]]
      add-stairs-up
      add-stairs-down))

(defn create-entities [area entities [num-fun type]]
  (nth (iterate (partial gen-entity {:type type} area) entities)
       (num-fun)))

(defn create-game [n floors player]
  (let [area (generate-dungeon area-width area-height room-attempts)
        player (if (nil? player) (gen-entity {:type :player} area []) player)
        entities (reduce (partial create-entities area)
                         player
                         (nth floors n))]
    {:area area
     :entities entities}))

(defn normalize-game [game]
  "Merges the :entity vector into the :area:tiles vector according to coordinates"
  (reduce (fn [area entity]
              (assoc-in area
                        [:tiles (yx->i (:width area) (:yx entity)) :tile]
                        (:type entity)))
          (:area game)
          (:entities game)))

(defn darken-game [area player-yx]
  "Darken a normalized game from the viewpoint of player-yx"
  (darken-dungeon area sight-range player-yx))

(defn game->web []
  (let [game (create-game (rand-int 6) floors nil)
        player-yx (->> game :entities (filter #(= (:type %) :player)) first :yx)]
    (-> game
        normalize-game
        (darken-game player-yx))))

