(ns clj-roguelike.events
  (:require [re-frame.core :as re-frame]
            [clj-roguelike.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))
