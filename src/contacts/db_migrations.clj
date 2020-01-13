(ns contacts.db-migrations
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as ragtime]
            [contacts.config :as config]))

(def ^:private db
  (format "jdbc:%s://%s:%s/%s?user=%s&password=%s"
          config/db-type config/db-host config/db-port config/db-name config/db-user config/db-password))

(defn- ^:private migration-config
  []
  (println "entered migration config")
  {:datastore  (jdbc/sql-database db)
   :migrations (jdbc/load-resources "migrations")
   :reporter   (fn [_ op id]
                 (case op
                   :up (println "Applying migration" id)
                   :down (println "Rolling back migration" id)))})

(defn migrate []
  (println "started migration")
  (ragtime/migrate (migration-config)))
