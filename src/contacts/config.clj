(ns contacts.config
  (:require [hikari-cp.core :refer :all]
            [clojure.java.jdbc :as jdbc]))

(def db-type "postgresql")

(def db-name "contacts")

(def db-host "localhost")

(def db-user "sa30")

(def db-password "")

(def db-port 5432)

(def pg-db {:dbtype   db-type
            :dbname   db-name
            :host     db-host
            :user     db-user
            :password db-password})

(def app-port 3000)

(def datasource-options {:auto-commit        true
                         :read-only          false
                         :connection-timeout 30000
                         :validation-timeout 5000
                         :idle-timeout       600000
                         :max-lifetime       1800000
                         :minimum-idle       10
                         :maximum-pool-size  10
                         :pool-name          "db-pool"
                         :adapter            db-type
                         :username           db-user
                         :password           db-password
                         :database-name      db-name
                         :server-name        db-host
                         :port-number        db-port
                         :register-mbeans    false})

(defonce datasource
         (delay (make-datasource datasource-options)))
