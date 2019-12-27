(ns contacts.handler
  [:require [bidi.ring :refer [make-handler]]
            [ring.util.response :as res]])

(defn pingHandler
  [request]
  (println request)
  (res/response "pong"))

(defn contactDetailsHandler
  [{:keys [route-params]}]
  (println route-params)
  (res/response (str "contacts details handler " (:id route-params))))

(def handler
  (make-handler ["/" {"ping"           pingHandler
                      ["contact/" :id] contactDetailsHandler}]))