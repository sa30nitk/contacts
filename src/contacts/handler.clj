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

(def internal-routes ["" {["contact/" :id] {:post contactDetailsHandler}}])

(def routes ["/" [["ping" pingHandler]
                  internal-routes
                  [true (constantly {:status 404})]]])

(def handler
  (make-handler routes))

