(ns hal9000.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [hal9000.events :as events]
   [hal9000.views :as views]
   [hal9000.config :as config]
   ))


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
  (mount-root))
