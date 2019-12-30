(ns contacts.db-migrations
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as ragtime]))

;(def spec
;  (pool/make-datasource-spec
;    {:subprotocol "postgresql"
;     :subname     "//localhost:5432/contacts"
;     :user        "gojek"
;     :password    ""}))

(def ^:private db
  (format "jdbc:%s://%s:%s/%s?user=%s&password=%s"
          "postgresql" "localhost" 5432 "contacts" "gojek" ""))

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
