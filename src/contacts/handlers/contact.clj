(ns contacts.handlers.contact
  (:require [ring.util.response :as res]
            [clojure.java.jdbc :as j]
            [clojure.walk :as walk]
            [contacts.config :as config]))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn get-contact-handler
  [{:keys [route-params]}]
  (try
    (j/with-db-connection [conn {:datasource @config/datasource}]
                          (let [contact (j/get-by-id conn :contact (:id route-params))]
                            (if (not= contact nil) (res/response contact) (res/not-found (str "Contact not found")))))
    (catch Exception e
      (.printStackTrace e)
      {:status  500
       :headers {}
       :body    {:error "internal server error"}})))

(defn delete-contact-handler
  [{:keys [route-params]}]
  (try
    (j/with-db-connection [conn {:datasource @config/datasource}]
                          (let [isDeleted (j/delete! config/pg-db :contact ["id = ?" (:id route-params)])]
                            (if (= isDeleted (list 1)) (res/response "Successfully deleted") (res/not-found (str "Contact not found")))))
    (catch Exception e
      (.printStackTrace e)
      {:status  500
       :headers {}
       :body    {:error "internal server error"}})))

(defn create-contact-handler
  [request]
  (let [body            (request :body)
        keywordizeBody  (walk/keywordize-keys body)
        contact         (select-keys keywordizeBody [:firstName :lastName :phoneNumber :email :favourite])
        contactWithUUID (merge contact {:id (uuid)})]
    (try
      (j/with-db-connection [conn {:datasource @config/datasource}]
                            (j/insert-multi! conn :contact [contactWithUUID]))
      (res/created (str (name (:scheme request)) "://" (:host (walk/keywordize-keys (:headers request))) "/v1/contacts/" (:id contactWithUUID)))
      (catch Exception e
        (.printStackTrace e)
        {:status  500
         :headers {}
         :body    {:error "internal server error"}}))))

(defn get-contacts-handler [request]
  (try
    (j/with-db-connection [conn {:datasource @config/datasource}]
                          (let [contacts (j/query conn ["select * from contact"])]
                            (if (not= contacts nil) (res/response contacts) (res/response {}))))
    (catch Exception e
      (.printStackTrace e)
      {:status  500
       :headers {}
       :body    {:error "internal server error"}})))

(defn patch-contact-handler
  [request]
  (try
    (j/with-db-connection [conn {:datasource @config/datasource}]
                          (let [body           (request :body)
                                keywordizeBody (walk/keywordize-keys body)
                                id             (:id keywordizeBody)
                                contact        (select-keys keywordizeBody [:firstName :lastName :phoneNumber :email :favourite])
                                isUpdated      (j/update! conn :contact contact ["id = ?" id])]
                            (if (= isUpdated (list 1)) (res/response "Successful update") (res/not-found "Update failed"))))
    (catch Exception e
      (.printStackTrace e)
      {:status  500
       :headers {}
       :body    {:error "internal server error"}})))
