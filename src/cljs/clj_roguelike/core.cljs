(ns clj-roguelike.core
  (:require-macros [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require [cljs.core.async :as async :refer (<! >! put! chan)]
            [taoensso.sente :as sente :refer (cb-success?)]
            [dumdom.core :as dumdom]
            [clj-roguelike.util :refer [hotkeys action]]
            [clj-roguelike.ui :refer [main-panel]]))

(defonce db (atom {:dialog :intro}))

(defonce socket (sente/make-channel-socket! "/chsk" {:type :auto}))
(def chsk-send! (:send-fn socket))

(defn set-game! [game-board]
  (swap! db assoc :game game-board))

(defn set-dialog! [kw]
  (swap! db assoc :dialog kw))

(defn valid-keychar? [keychar]
  (let [valids (conj (set hotkeys)
                     "ArrowLeft"
                     "ArrowRight"
                     "ArrowUp"
                     "ArrowDown")]
    (contains? valids keychar)))

(defn handle-keys! [e]
  (let [walk! #(chsk-send! [:game/action {:type :walk, :dir %}]
                           5000
                           set-game!)
        use! #(chsk-send! [:game/action {:type :use, :hotkey %}]
                          5000
                          set-game!)
        keychar (.-key e)]
    (when (valid-keychar? keychar)
      (case keychar
        "ArrowLeft"  (walk! :west)
        "ArrowRight" (walk! :east)
        "ArrowUp"    (walk! :north)
        "ArrowDown"  (walk! :south)
        (use! keychar)))))

(defn request-game-board! []
  (chsk-send! [:game/start] 5000
              (fn [game-board]
                (set-game! game-board)
                (.addEventListener js/document "keydown" handle-keys!))))

(defmulti -event-msg-handler :id)

(defn event-msg-handler
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg))

(defmethod -event-msg-handler :default
  [{:as ev-msg :keys [event]}]
  (println "Unhandled event" event))

(defmethod -event-msg-handler :chsk/state
  [{:as ev-msg :keys [event]}]
  (when (:first-open? (second (second event)))
    (request-game-board!)))

(defonce router_ (atom nil))
(defn stop-router! [] (when-let [stop-f @router_] (stop-f)))
(defn start-router! []
  (stop-router!)
  (reset! router_
          (sente/start-client-chsk-router!
            (:ch-recv socket) event-msg-handler)))

(defn dev-setup []
  (let [debug? ^boolean goog.DEBUG]
    (when debug?
      (enable-console-print!)
      (println "dev mode"))))

(defn render [state]
  (let [$m {:$close (action #(set-dialog! nil))
            :$open-intro (action #(set-dialog! :intro))
            :$open-copy (action #(set-dialog! :copy))
            :$open-load (action #(set-dialog! :load))
            :$open-new (action #(set-dialog! :new))}]
    (dumdom/render (main-panel state $m)
                   (.getElementById js/document "app"))))

(defn on-js-reload []
  (render @db)
  nil)

(defn ^:export init []
  (dev-setup)
  (add-watch db :render #(render %4))
  (start-router!)
  nil)
