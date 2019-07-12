(ns hal9000.events
  (:require
   [re-frame.core :as re-frame]
   [hal9000.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
