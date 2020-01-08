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

(defn getContact
  [{:keys [route-params]}]
  (println route-params)
  (let [contact (j/get-by-id pg-db :contact (:id route-params))]
    (prn contact)
    (if (not= contact nil) (res/response contact) (res/not-found (str "Contact not found")))))

(defn createContactHandler
  [request]
  (println request)
  (let [body            (request :body)
        keywordizeBody  (walk/keywordize-keys body)
        contact         (select-keys keywordizeBody [:firstName :lastName :phoneNumber :email :favourite])
        contactWithUUID (merge contact {:id (uuid)})]
    (j/insert-multi! pg-db :contact [contactWithUUID])
    (res/created (str "http://localhost:3000/contacts/" (:id contactWithUUID)))))

