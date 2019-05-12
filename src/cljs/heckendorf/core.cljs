(ns heckendorf.core
  (:require-macros [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require [cljs.core.async :as async :refer (<! >! put! chan)]
            [taoensso.sente :as sente :refer (cb-success?)]
            [dumdom.core :as dumdom]
            [heckendorf.data :refer [hotkeys hotkey->index]]
            [heckendorf.util :refer [action get-uid set-uid!]]
            [heckendorf.ui :refer [main-panel]]))

(defonce db (atom {:dialog :intro, :res {}, :offline? false}))

(defn with-uid [opts]
  (let [uid (get-uid)]
    (if (some? uid)
      (assoc opts :client-id uid)
      opts)))

(def csrf-token
  (when-let [el (.getElementById js/document "sente-csrf-token")]
    (.getAttribute el "data-csrf-token")))

(defonce socket
  (sente/make-channel-socket-client! "/chsk" csrf-token (with-uid {:type :auto})))
(def chsk-send! (:send-fn socket))

(defn set-input! [k v]
  (swap! db assoc-in [:input k] v))

(defn set-dialog! [kw]
  (swap! db assoc :dialog kw))

(defn set-response! [k s]
  (swap! db assoc-in [:res k] s))

(defn clear-response! []
  (swap! db assoc :res {}))

(defn set-leaderboard! [lb]
  (swap! db assoc :leaderboard lb))

(defn set-offline! [v]
  (swap! db assoc :offline? v))

(defn seen-tiles [prevts newts]
  (mapv (fn [prevt newt]
          (if (and (= (:tile newt) :dark)
                   (not= (:tile prevt) :dark))
            (assoc prevt :seen? true)
            newt))
        prevts
        newts))

(defn set-game!
  "game-board can also be a keyword like :game-over, in which case we want to
  set :dialog to this value."
  [game-board]
  (if (map? game-board)
    (swap! db update :game (fn [{old-tiles :tiles :as old-board}]
                             (let [same-floor? (apply = (map #(get-in % [:player :floor])
                                                             [old-board game-board]))
                                   same-game? (not= (:new-game? game-board) true)]
                               (if (and same-floor? same-game?)
                                 (update game-board :tiles (partial seen-tiles old-tiles))
                                 game-board))))
    (set-dialog! game-board)))

(let [prev-key (atom nil)
      timeout (atom nil)]
  (defn handle-keys! [e]
    (when (and (nil? (:dialog @db))
               (not (.-metaKey e))
               (not (.-ctrlKey e))
               (not (.-altKey e)))
      (let [rawkey (.-key e)
            keychar (cond-> rawkey (= (count rawkey) 1) .toLowerCase)
            shift? (.-shiftKey e)
            move! #(chsk-send! [:game/action {:type (if shift?
                                                      :dash
                                                      :walk)
                                              :dir %}]
                               5000
                               set-game!)
            use! #(chsk-send! [:game/action {:type :use, :hotkey %}]
                              5000
                              set-game!)
            rest! #(chsk-send! [:game/action {:type :rest}]
                               5000
                               set-game!)
            timeout! (fn [action!]
                       (let [prev @prev-key]
                         (reset! prev-key keychar)
                         (js/clearTimeout @timeout)
                         (reset! timeout
                                 (js/setTimeout
                                   #(do (reset! prev-key nil)
                                        (action! prev))
                                   50))))]
        (when (contains? hotkeys keychar)
          (.preventDefault e)
          (case keychar
            ; rest
            "r" (rest!)
            ; movement with vim bindings
            "h" (move! :west)
            "j" (move! :south)
            "k" (move! :north)
            "l" (move! :east)
            "n" (move! :south-west)
            "m" (move! :south-east)
            "i" (move! :north-west)
            "o" (move! :north-east)
            ; diagonal movement and rest with numpad
            "Home"     (move! :north-west)
            "PageUp"   (move! :north-east)
            "Clear"    (rest!)
            "End"      (move! :south-west)
            "PageDown" (move! :south-east)
            ; movement and rest with numpad (numlock active)
            "7" (move! :north-west) "8" (move! :north) "9" (move! :north-east)
            "4" (move! :west)       "5" (rest!)        "6" (move! :east)
            "1" (move! :south-west) "2" (move! :south) "3" (move! :south-east)
            ; arrow keys
            "ArrowLeft"  (timeout! #(condp = %
                                      "ArrowUp" (move! :north-west)
                                      "ArrowDown" (move! :south-west)
                                      (move! :west)))
            "ArrowRight" (timeout! #(condp = %
                                      "ArrowUp" (move! :north-east)
                                      "ArrowDown" (move! :south-east)
                                      (move! :east)))
            "ArrowUp"    (timeout! #(condp = %
                                      "ArrowLeft" (move! :north-west)
                                      "ArrowRight" (move! :north-east)
                                      (move! :north)))
            "ArrowDown"  (timeout! #(condp = %
                                      "ArrowLeft" (move! :south-west)
                                      "ArrowRight" (move! :south-east)
                                      (move! :south)))
            (when (hotkey->index (get-in @db [:game :player :inventory]) keychar)
              (use! keychar))))))))

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

(defn request-leaderboard! []
  (chsk-send! [:game/leaderboard]
              5000
              (fn [leaderboard]
                (set-leaderboard! (sort-by first leaderboard))
                (set-dialog! :leaderboard))))

(defn submit-name! [player-name]
  (if (> (count player-name) 20)
    (set-response! :victory "Please enter a name no longer than 20 characters")
    (chsk-send! [:game/submit player-name]
                5000
                (fn [res]
                  (set-response! :leaderboard res)
                  (request-leaderboard!)))))

(defmulti -event-msg-handler :id)

(defn event-msg-handler
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg))

(defmethod -event-msg-handler :default
  [{:as ev-msg :keys [event]}]
  (println "Unhandled event" event))

(defmethod -event-msg-handler :chsk/state
  [{:as ev-msg :keys [event]}]
  (let [{:keys [first-open? uid open?]} (second (second event))]
    (cond
      first-open? (do (set-uid! uid)
                      (request-game!))
      (and (not open?)
           (not (get @db :offline?))) (set-offline! true)
      (and open?
           (get @db :offline?)) (set-offline! false)
      :else nil)))

(defmethod -event-msg-handler :chsk/handshake [_] nil)

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
  (let [$m {:$close (action #(do (set-dialog! nil)
                                 (clear-response!)))
            :$open-intro (action #(set-dialog! :intro))
            :$open-copy (action #(set-dialog! :copy))
            :$open-load (action #(set-dialog! :load))
            :$open-new (action #(set-dialog! :new))
            :$open-leaderboard (action #(request-leaderboard!))
            :$new-game (action #(do (request-new-game!)
                                    (set-dialog! nil)
                                    (clear-response!)))
            :$load-game (action #(do (set-uid! (get-in @db [:input :code]))
                                     (.reload js/location)))
            :$submit-name (action #(submit-name! (get-in @db [:input :name])))
            :$input-code (fn [e]
                           (set-input! :code (.. e -target -value)))
            :$input-name (fn [e]
                           (set-input! :name (.. e -target -value)))}]
    (dumdom/render (main-panel state $m)
                   (.getElementById js/document "app"))))

(defn on-js-reload []
  (render @db)
  nil)

(defn ^:export init []
  (dev-setup)
  (render @db)
  (add-watch db :render #(render %4))
  (start-router!)
  nil)
