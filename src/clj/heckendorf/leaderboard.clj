(ns heckendorf.leaderboard
  (:require [clojure.edn :as edn]
            [heckendorf.data :refer [file]]
            [heckendorf.game :refer [get-game-actions won-game?]]))

(def ^:const leaderboard "leaderboard.edn")
(def ^:const max-scores 100)

(def leaderboard-atom
  "Atom to handle the `leaderboard` file, to support concurrent access."
  (atom
    (edn/read-string (try (slurp (file leaderboard))
                          (catch java.io.FileNotFoundException _
                          ;; Create file if it doesn't exist and return empty map.
                                 (let [init (prn-str {})]
                                   (spit (file leaderboard) init)
                                   init))))))

(add-watch leaderboard-atom :save
           #(spit (file leaderboard) (prn-str %4)))

(defn worst-score
  "Return lkey of score with highest action count, meaning the lowest score."
  [lmap]
  (key (apply max-key (comp second key) lmap)))

(defn lmap->placements
  "Adds leaderboard placement to lmap as the `(second (val kv))`."
  [lmap]
  (->> lmap
       (into (sorted-map-by #(apply < (map second %&))))
       (map-indexed (fn [i [k v]] (update [k v] 1 vector i)))
       (into {})))

(defn place-score
  "Return placement number of the given lkey in lmap."
  [lmap lkey]
  (some-> (lmap->placements lmap) (get lkey) second inc))

(defn placement-str [lmap lkey]
  (let [place (place-score lmap lkey)]
    (str "You are currently placed " place " in the leaderboard")))

(defn get-leaderboard! []
  (map (fn [[[_ actions] [player-name index]]]
         [(inc index) actions player-name])
       (lmap->placements @leaderboard-atom)))

(defn add-score! [lkey player-name]
  (swap! leaderboard-atom
         (fn [lmap]
           (-> lmap
               (cond-> (>= (count lmap) max-scores) (dissoc (worst-score lmap)))
               (assoc lkey player-name)))))

(defn update-leaderboard! [client-id name-data reply-fn]
  (let [lmap @leaderboard-atom
        actions (get-game-actions client-id)
        lkey [client-id actions]
        player-name (subs name-data 0 (min (count name-data) 20))]
    (cond
      (or (not (won-game? client-id))
          (nil? actions)) (reply-fn "You cheater You have not won")
      (contains? lmap lkey) (reply-fn "Your score has already been recorded")
      (and (>= (count lmap) max-scores)
           (>= actions (second (worst-score lmap)))) (reply-fn "Your score wasn't good enough")
      :else (-> (add-score! lkey player-name)
                (placement-str lkey)
                reply-fn))))
