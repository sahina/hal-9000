(defproject hal9000 "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.clojure/core.async "0.4.500"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.7"]
                 [compojure "1.6.1"]
                 [ring "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [yogthos/config "1.1.4"]
                 [cheshire "5.8.1"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev  {:dependencies   [[binaryage/devtools "0.9.10"]]
                    :plugins        [[lein-figwheel "0.5.18"]]
                    :resource-paths ["config/dev"]
                    :main           hal9000.server}
             :prod {:resource-paths ["config/dev"]
                    :main           hal9000.server}}

  :cljsbuild {:builds [{:id           "dev"
                        :source-paths ["src/cljs"]
                        :figwheel     {:on-jsload "hal9000.core/mount-root"}
                        :compiler     {:main                 hal9000.core
                                       :output-to            "resources/public/js/compiled/app.js"
                                       :output-dir           "resources/public/js/compiled/out"
                                       :asset-path           "js/compiled/out"
                                       :source-map-timestamp true
                                       :preloads             [devtools.preload]
                                       :external-config      {:devtools/config {:features-to-install :all}}}}

                       {:id           "min"
                        :source-paths ["src/cljs"]
                        :compiler     {:main            hal9000.core
                                       :output-to       "resources/public/js/compiled/app.js"
                                       :optimizations   :advanced
                                       :closure-defines {goog.DEBUG false}
                                       :pretty-print    false}}]}
  :uberjar-name "hal9000.jar"
  )
