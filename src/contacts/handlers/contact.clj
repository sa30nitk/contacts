(ns contacts.handlers.contact
  (:require [ring.util.response :as res]))

(defn contactDetailsHandler
  [{:keys [route-params]}]
  (println route-params)
  (res/response (str "contacts details handler " (:id route-params))))
