(ns contacts.config)

(def db-type "postgressql")

(def db-name "contacts")

(def db-host "localhost")

(def db-user "gojek")

(def db-password "")

(def db-port 5432)

(def pg-db {:dbtype   db-type
            :dbname   db-name
            :host     db-host
            :user     db-user
            :password db-password})


(def app-port 3000)