(ns contacts.handlers.contact
  (:require [ring.util.response :as res]
            [clojure.java.jdbc :as j]
            [clojure.walk :as walk]))

(def pg-db {:dbtype   "postgresql"
            :dbname   "contacts"
            :host     "localhost"
            :user     "gojek"
            :password ""})

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn contactDetailsHandler
  [{:keys [route-params]}]
  (println route-params)
  (j/insert-multi! pg-db :contact
                   [{:id (:id route-params)}])
  (res/response (str "contacts details handler " (:id route-params))))

(defn createContactHandler
  [{:keys [body]}]
  (println body)
  (prn (walk/keywordize-keys body))
  (let [contactOnlyMap (select-keys (walk/keywordize-keys body)  [:firstName :lastName :phoneNumber :email :favourite])
        contactOnlyMapWithUUID (merge contactOnlyMap {:id (uuid)})]
    (j/insert-multi! pg-db :contact [contactOnlyMapWithUUID]))
  (res/response "contact created successfully"))

