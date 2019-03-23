(ns clj-roguelike.game
    (:require [clj-roguelike.entity :refer [gen-entity entity-with reset-id]]
              [clj-roguelike.dungeon :refer [generate-dungeon darken-dungeon yx->i]]
              [clj-roguelike.random :refer [rand-range]]
              [clj-roguelike.action :refer [effect-entities]]))

(def ^:const area-width 30)
(def ^:const area-height 30)
(def ^:const room-attempts 50)
(def ^:const sight-range 10)

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
  ;; ID should correspond to the entity's vector index.
  (reset-id -1)
  (let [area (generate-dungeon area-width area-height room-attempts)
        player ((fnil gen-entity {:type :player}) player area [])
        entities (reduce (partial create-entities area)
                         player
                         (nth floors n))]
    {:area area
     :entities entities}))

(defn normalize-game [game]
  "Merges the :entity vector into the :area:tiles vector according to coordinates"
  (reduce (fn [area {:keys [type yx game-over]}]
            (cond-> area
              (not= type :dead)
              (assoc-in [:tiles (yx->i (:width area) yx)]
                        (cond-> {:tile type}
                                (= type :player) (assoc :game-over game-over)))))
          (:area game)
          (:entities game)))

(defn darken-game [area player-yx]
  "Darken a normalized game from the viewpoint of player-yx"
  (darken-dungeon area sight-range player-yx))

(defn prepare-game [game]
  "Processes the game state before it is sent to client."
  (let [player (-> game :entities (get 0))]
    (-> game
        normalize-game
        (darken-game (:yx player))
        ;; Add the player object so client can display stats
        (assoc :player player))))

(defonce game-atom (atom {}))

(defn get-game [client-id]
  (if (contains? @game-atom client-id)
    (prepare-game (get @game-atom client-id))
    (let [new-game (create-game 0 floors nil)]
      (swap! game-atom assoc client-id new-game)
      (prepare-game new-game))))

(defn update-game [client-id entity-id action]
  (let [prev-game (get @game-atom client-id)
        next-game (-> prev-game
                      (assoc-in [:entities entity-id :next-action] action)
                      effect-entities)
        [prev-floor next-floor] (map #(get-in % [:entities 0 :floor])
                                     [prev-game
                                      next-game])
        game-over? (some? (get-in prev-game [:entities 0 :game-over]))]
    (cond game-over? (prepare-game prev-game)
          (= prev-floor next-floor) (do (swap! game-atom assoc client-id next-game)
                                      (prepare-game next-game))
          :else
          (let [player (get-in next-game [:entities 0])
                new-game (create-game next-floor floors player)]
            (swap! game-atom assoc client-id new-game)
            (prepare-game new-game)))))
