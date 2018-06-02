(ns clj-roguelike.web
    (:require [org.httpkit.server :as http-kit]
              [taoensso.sente.server-adapters.http-kit :refer (get-sch-adapter)]
              [ring.middleware.defaults]
              [compojure.core :as comp :refer (defroutes GET POST)]
              [compojure.route :as route]
              [hiccup.core :as hiccup]
              [clojure.core.async :as async :refer (<! <!! >! >!! put! chan go go-loop)]
              [taoensso.timbre :as timbre :refer (tracef debugf infof warnf errorf)]
              [taoensso.sente :as sente]
              [clj-roguelike.game :refer [game->web]]))

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

(defmulti -event-msg-handler
  "Multimethod to handle Sente `event-msg`s"
  :id)

(defn event-msg-handler
  "Wraps `-event-msg-handler` with logging, error catching, etc."
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg))

(defmethod -event-msg-handler
  :default ; Default/fallback case (no other matching handler)
  [{:as ev-msg :keys [event id ?data ring-req ?reply-fn send-fn]}]
  (let [session (:session ring-req)
        uid     (:uid     session)]
    (debugf "Unhandled event: %s" event)
    (when ?reply-fn
      (?reply-fn {:umatched-event-as-echoed-from-from-server event}))))

(defmethod -event-msg-handler :game/start
  [{:as ev-msg :keys [?reply-fn]}]
  (?reply-fn (game->web)))

(defonce router_ (atom nil))
(defn stop-router! [] (when-let [stop-fn @router_] (stop-fn)))
(defn start-router! []
  (stop-router!)
  (reset! router_
          (sente/start-server-chsk-router!
            ch-chsk event-msg-handler)))

(defonce web-server_ (atom nil))
(defn stop-web-server! [] (when-let [stop-fn @web-server_] (stop-fn)))
(defn start-web-server! [dev?]
  (stop-web-server!)
  (let [port (if dev? 9090 80)
        ring-handler (var main-ring-handler)
        [port stop-fn]
        (let [stop-fn (http-kit/run-server ring-handler {:port port})]
          [(:local-port (meta stop-fn)) (fn [] (stop-fn :timeout 100))])
        uri (format "http://localhost:%s/" port)]
    (infof "Web server is running at `%s`" uri)
    (reset! web-server_ stop-fn)))

(defn in-dev? [args]
  (not= args '("prod")))

(defn stop! [] (stop-router!) (stop-web-server!))
(defn start! [args]
  (start-router!)
  (start-web-server! (in-dev? args)))

(defn -main "For `lein run`, etc." [& args] (start! args))
