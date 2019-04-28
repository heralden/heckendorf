(ns heckendorf.game
    (:require [heckendorf.entity :refer [gen-entity entity-with reset-id]]
              [heckendorf.dungeon :refer [generate-dungeon darken-dungeon yx->i]]
              [heckendorf.random :refer [rand-range]]
              [heckendorf.action :refer [effect-entities]]))

(def ^:const area-width 40)
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
  (-> [[[#(rand-range 6 10) :monster/spider]
        [#(rand-range 0 2)  :monster/skeleton]
        [#(rand-range 2 5)  :chest/common]
        [#(rand-range 0 2)  :chest/uncommon]]
       [[#(rand-range 4 8)  :monster/spider]
        [#(rand-range 3 5)  :monster/skeleton]
        [#(rand-range 0 2)  :monster/zombie]
        [#(rand-range 1 3)  :chest/common]
        [#(rand-range 2 4)  :chest/uncommon]]
       [[#(rand-range 3 6)  :monster/spider]
        [#(rand-range 6 7)  :monster/skeleton]
        [#(rand-range 4 7)  :monster/zombie]
        [#(rand-range 1 2)  :monster/ghost]
        [#(rand-range 0 3)  :chest/common]
        [#(rand-range 3 5)  :chest/uncommon]]
       [[#(rand-range 2 5)  :monster/spider]
        [#(rand-range 4 8)  :monster/skeleton]
        [#(rand-range 6 9)  :monster/zombie]
        [#(rand-range 2 3)  :monster/ghost]
        [#(identity 1)      :monster/grim-reaper]
        [#(identity 1)      :monster/drake]
        [#(rand-range 1 3)  :chest/common]
        [#(rand-range 2 4)  :chest/uncommon]
        [#(rand-range 1 3)  :chest/rare]
        [#(identity 1)      :chest/epic]]
       [[#(rand-range 1 4)  :monster/spider]
        [#(rand-range 3 8)  :monster/skeleton]
        [#(rand-range 6 9)  :monster/zombie]
        [#(rand-range 4 5)  :monster/ghost]
        [#(identity 2)      :monster/grim-reaper]
        [#(identity 2)      :monster/drake]
        [#(rand-range 1 3)  :chest/common]
        [#(rand-range 2 4)  :chest/uncommon]
        [#(rand-range 1 3)  :chest/rare]
        [#(identity 2)      :chest/epic]]
       [[#(rand-range 2 4)  :monster/skeleton]
        [#(rand-range 3 5)  :monster/zombie]
        [#(rand-range 2 3)  :monster/ghost]
        [#(rand-range 1 2)  :monster/grim-reaper]
        [#(identity 1)      :monster/drake]
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

(defn new-game [client-id]
  (let [game (create-game 0 floors nil)]
    (swap! game-atom assoc client-id game)
    (prepare-game game)))

(defn get-game [client-id]
  (if (contains? @game-atom client-id)
    (prepare-game (get @game-atom client-id))
    (new-game client-id)))

(defn update-game [client-id entity-id action]
  (let [prev-game (get @game-atom client-id)
        next-game (-> prev-game
                      (assoc-in [:entities entity-id :next-action] action)
                      (update-in [:entities entity-id :actions] inc)
                      effect-entities)
        [prev-floor next-floor] (map #(get-in % [:entities 0 :floor])
                                     [prev-game
                                      next-game])
        game-over? (some? (get-in prev-game [:entities 0 :game-over]))]
    (cond game-over? :game-over
          (= prev-floor next-floor) (do (swap! game-atom assoc client-id next-game)
                                      (prepare-game next-game))
          :else
          (let [player (get-in next-game [:entities 0])
                new-game (create-game next-floor floors player)]
            (swap! game-atom assoc client-id new-game)
            (prepare-game new-game)))))
