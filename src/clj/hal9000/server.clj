(ns hal9000.server
  (:require [hal9000.handler :refer [handler dev-handler]]
            [config.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn -main [& args]
  (let [port (Integer/parseInt (or (env :port) "3000"))]
    (println (:profile env))
    (if (= (:profile env) "dev")
      (run-jetty dev-handler {:port port :join? false})
      (run-jetty handler {:port port :join? false}))))