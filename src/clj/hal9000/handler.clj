(ns hal9000.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer :all]
            [config.core :refer [env]]
            [compojure.route :as route]
            [cheshire.core :as json]
            [clojure.java.io :as io]))


(defroutes routes
           (GET "/about" [] (resource-response "about.html" {:root "public"}))
           (route/resources "/")
           (route/not-found (slurp (io/resource "public/404.html"))))


(def dev-handler (-> routes
                     wrap-json-response
                     wrap-json-body
                     (wrap-defaults api-defaults)
                     wrap-reload))

(def handler routes)