(ns contacts.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]
            [contacts.routes :refer [handler]]
            [contacts.db-migrations :as migrations]))

(def ^:private app
  (-> handler
      wrap-session
      wrap-flash))

(defn -main [& args]
  (println "Started main")
  (case (first args)
    "migrate" (migrations/migrate)
    (jetty/run-jetty app {:port 3000})))

