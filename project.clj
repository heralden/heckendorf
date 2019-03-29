(defproject heckendorf "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.516"]
                 [http-kit "2.3.0"]
                 [com.taoensso/sente "1.14.0-RC2"]
                 [com.taoensso/timbre "4.10.0"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.2"]
                 [compojure "1.6.0"]
                 [cjohansen/dumdom "2019.02.03-3"]
                 [garden "1.3.6"]
                 [herb "0.7.2"]]

  :main heckendorf.web

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj", "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css" "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :aliases {"start" ["do" ["cljsbuild" "once" "dev"] "run"]}
  ;; For a developer environment, you'll want to open two terminals with
  ;; `lein repl` (then run `(-main)`) and `lein figwheel dev`.

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.4"]
                   [cider/piggieback "0.4.0"]
                   [figwheel-sidecar "0.5.16"]
                   [midje "1.9.1"]]
    :source-paths ["src/cljs"]
    :plugins      [[lein-figwheel "0.5.16"]
                   [lein-doo "0.1.8"]]
    :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}}

   :uberjar
   {:prep-tasks ["compile" ["cljsbuild" "once" "min"]]
    :aot :all}}
   ;; Note: When using openjdk-9 you might need to add flags
   ;; `java --add-modules java.xml.bind -jar my-uberjar.jar`

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "heckendorf.core/on-js-reload"}
     :compiler     {:main                 heckendorf.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    :warnings             {:fn-arity false}}}


    {:id           "min"
     :source-paths ["src/cljs"]
     :jar true
     :compiler     {:main            heckendorf.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false
                    :warnings        {:fn-arity false}}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          heckendorf.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}]})



