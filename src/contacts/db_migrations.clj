(ns contacts.db-migrations
  (require [[ragtime.jdbc :as jdbc]
            [ragtime.repl :as ragtime]]))


(def config
  {:datastore (jdbc/sql-database {:connection-uri ""})
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (ragtime/migrate config))

