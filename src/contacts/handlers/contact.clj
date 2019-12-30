(ns contacts.handlers.contact
  (:require [ring.util.response :as res]
            [clojure.java.jdbc :as j]))

(def pg-db {:dbtype "postgresql"
            :dbname "contacts"
            :host "localhost"
            :user "gojek"
            :password ""})


(defn contactDetailsHandler
  [{:keys [route-params]}]
  (println route-params)
  (j/insert-multi! pg-db :contact
                   [{:id (:id route-params)}])
  (res/response (str "contacts details handler " (:id route-params))))

