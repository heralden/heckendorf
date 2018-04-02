(ns clj-roguelike.web
    (:require [org.httpkit.server :as http-kit]
              [taoensso.sente.server-adapters.http-kit :refer (get-sch-adapter)]
              [ring.middleware.defaults]
              [compojure.core :as comp :refer (defroutes GET POST)]
              [compojure.route :as route]
              [hiccup.core :as hiccup]
              [clojure.core.async :as async :refer (<! <!! >! >!! put! chan go go-loop)]
              [taoensso.timbre :as timbre :refer (tracef debugf infof warnf errorf)]
              [taoensso.sente :as sente]))

(let [packer :edn
      chsk-server (sente/make-channel-socket-server!
                    (get-sch-adapter) {:packer packer})
      {:keys [ch-recv send-fn connected-uids
              ajax-post-fn ajax-get-or-ws-handshake-fn]}
      chsk-server]
  
  (def ring-ajax-post ajax-post-fn)
  (def ring-ajax-get-or-ws-handshake ajax-get-or-ws-handshake-fn)
  (def ch-chsk ch-recv)
  (def chsk-send! send-fn)
  (def connected-uids connected-uids))

(add-watch connected-uids :connected-uids
           (fn [_ _ old new]
               (when (not= old new)
                 (infof "Connected uids change: %s" new))))

(defn landing-pg-handler [ring-req]
  (hiccup/html
    [:html {:lang "en"}
     [:head
      [:meta {:charset "utf-8"}]]
     [:body
      [:div {:id "app"}]
      [:script {:src "js/compiled/app.js"}]
      [:script "clj_roguelike.core.init();"]]]))

(defroutes ring-routes
  (GET "/" ring-req (landing-pg-handler ring-req))
  (GET "/chsk" ring-req (ring-ajax-get-or-ws-handshake ring-req))
  (POST "/chsk" ring-req (ring-ajax-post ring-req))
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(def main-ring-handler
  (ring.middleware.defaults/wrap-defaults
    ring-routes ring.middleware.defaults/site-defaults))

(defn start-game! []
  (doseq [uid (:any @connected-uids)]
    (chsk-send! uid
      [:some/broadcast
       {:what-is-this "Game data shoudl go here!"}])))

(defn test-fast-server>user-pushes
  "Quickly pushes 100 events to all connected users. Note that this'll be
  fast+reliable even over Ajax!"
  []
  (doseq [uid (:any @connected-uids)]
    (doseq [i (range 100)]
      (chsk-send! uid [:fast-push/is-fast (str "hello " i "!!")]))))

(comment (test-fast-server>user-pushes))

(defonce broadcast-enabled?_ (atom true))

(defn start-example-broadcaster!
  "As an example of server>user async pushes, setup a loop to broadcast an
  event to all connected users every 10 seconds"
  []
  (let [broadcast!
        (fn [i]
          (let [uids (:any @connected-uids)]
            (debugf "Broadcasting server>user: %s uids" (count uids))
            (doseq [uid uids]
              (chsk-send! uid
                [:some/broadcast
                 {:what-is-this "An async broadcast pushed from server"
                  :how-often "Every 10 seconds"
                  :to-whom uid
                  :i i}]))))]

    (go-loop [i 0]
      (<! (async/timeout 10000))
      (when @broadcast-enabled?_ (broadcast! i))
      (recur (inc i)))))

(defmulti -event-msg-handler
  "Multimethod to handle Sente `event-msg`s"
  :id ; Dispatch on event-id
  )
 
(defn event-msg-handler
  "Wraps `-event-msg-handler` with logging, error catching, etc."
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg) ; Handle event-msgs on a single thread
  ;; (future (-event-msg-handler ev-msg)) ; Handle event-msgs on a thread pool
  )

(defmethod -event-msg-handler
  :default ; Default/fallback case (no other matching handler)
  [{:as ev-msg :keys [event id ?data ring-req ?reply-fn send-fn]}]
  (let [session (:session ring-req)
        uid     (:uid     session)]
    (debugf "Unhandled event: %s" event)
    (when ?reply-fn
      (?reply-fn {:umatched-event-as-echoed-from-from-server event}))))

(defmethod -event-msg-handler :example/test-rapid-push
  [ev-msg] (test-fast-server>user-pushes))

(defmethod -event-msg-handler :example/toggle-broadcast
  [{:as ev-msg :keys [?reply-fn]}]
  (let [loop-enabled? (swap! broadcast-enabled?_ not)]
    (?reply-fn loop-enabled?)))

(defmethod -event-msg-handler :game/start
  [{:as ev-msg :keys [?reply-fn]}]
  (?reply-fn "Here is your game data!"))

(defonce router_ (atom nil))
(defn stop-router! [] (when-let [stop-fn @router_] (stop-fn)))
(defn start-router! []
  (stop-router!)
  (reset! router_
          (sente/start-server-chsk-router!
            ch-chsk event-msg-handler)))

(defonce web-server_ (atom nil))
(defn stop-web-server! [] (when-let [stop-fn @web-server_] (stop-fn)))
(defn start-web-server! [& [port]]
  (stop-web-server!)
  (let [port (or port 0)
        ring-handler (var main-ring-handler)
        [port stop-fn]
        (let [stop-fn (http-kit/run-server ring-handler {:port port})]
          [(:local-port (meta stop-fn)) (fn [] (stop-fn :timeout 100))])
        uri (format "http://localhost:%s/" port)]
    (infof "Web server is running at `%s`" uri)
    (reset! web-server_ stop-fn)))

(defn stop! [] (stop-router!) (stop-web-server!))
(defn start! [] 
  (start-router!) 
  (start-web-server!) 
  (start-example-broadcaster!))

(defn -main "For `lein run`, etc." [] (start!))
