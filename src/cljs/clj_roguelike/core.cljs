(ns clj-roguelike.core
  (:require-macros [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require [cljs.core.async :as async :refer (<! >! put! chan)]
            [taoensso.sente :as sente :refer (cb-success?)]
            [dumdom.core :as dumdom]
            [clj-roguelike.util :refer [hotkeys action get-uid set-uid!]]
            [clj-roguelike.ui :refer [main-panel]]))

(defonce db (atom {:dialog :intro}))

(defn with-uid [opts]
  (let [uid (get-uid)]
    (if (some? uid)
      (assoc opts :client-id uid)
      opts)))

(defonce socket
  (sente/make-channel-socket-client! "/chsk" nil (with-uid {:type :auto})))
(def chsk-send! (:send-fn socket))

(defn set-input! [k v]
  (swap! db assoc-in [:input k] v))

(defn set-dialog! [kw]
  (swap! db assoc :dialog kw))

(defn set-game!
  "game-board can also be a keyword like :game-over, in which case we want to
  set :dialog to this value."
  [game-board]
  (if (map? game-board)
    (swap! db assoc :game game-board)
    (set-dialog! game-board)))

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

(defn request-game! []
  (chsk-send! [:game/start]
              5000
              (fn [game-board]
                (set-game! game-board)
                (.addEventListener js/document "keydown" handle-keys!))))

(defn request-new-game! []
  (chsk-send! [:game/new]
              5000
              (fn [game-board]
                (set-game! game-board))))

(defmulti -event-msg-handler :id)

(defn event-msg-handler
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg))

(defmethod -event-msg-handler :default
  [{:as ev-msg :keys [event]}]
  (println "Unhandled event" event))

(defmethod -event-msg-handler :chsk/state
  [{:as ev-msg :keys [event]}]
  (let [{:keys [first-open? uid]} (second (second event))]
    (when first-open?
      (set-uid! uid)
      (request-game!))))

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
            :$open-new (action #(set-dialog! :new))
            :$new-game (action #(do (request-new-game!)
                                    (set-dialog! nil)))
            :$load-game (action #(do (set-uid! (get-in @db [:input :code]))
                                     (.reload js/location)))
            :$input-code (fn [e]
                           (set-input! :code (.. e -target -value)))}]
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
