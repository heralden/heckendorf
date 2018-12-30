(ns clj-roguelike.core
  (:require-macros [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [clj-roguelike.events :as events]
            [clj-roguelike.views :as views]
            [clj-roguelike.config :as config]
            [cljs.core.async :as async :refer (<! >! put! chan)]
            [taoensso.sente :as sente :refer (cb-success?)]))

(let [{:keys [chsk ch-recv send-fn state]}
      (sente/make-channel-socket! "/chsk" {:type :auto })]
  (def chsk chsk)
  (def ch-chsk ch-recv)
  (def chsk-send! send-fn)
  (def chsk-state state))

(defn update-game-board! [game-board]
  (re-frame/dispatch [::events/game-state game-board]))

(defn valid-keychar? [keychar]
  (let [valids (conj (set views/hotkeys)
                     "ArrowLeft"
                     "ArrowRight"
                     "ArrowUp"
                     "ArrowDown")]
    (contains? valids keychar)))

(defn handle-keys [e]
  (let [walk! #(chsk-send! [:game/action {:type :walk, :dir %}]
                          5000
                          update-game-board!)
        use! #(chsk-send! [:game/action {:type :use, :hotkey %}]
                          5000
                          update-game-board!)
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
        (update-game-board! game-board)
        (.addEventListener js/document "keydown" handle-keys))))

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
            ch-chsk event-msg-handler)))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root)
  (start-router!))
