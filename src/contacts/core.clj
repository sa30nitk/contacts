(ns contacts.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [contacts.routes :refer [handler]]
            [contacts.db-migrations :as migrations]))

(defn wrap-prn-response
  [handler]
  (fn
    ([request]
     (let [response (handler request)]
       (prn response)
       response))
    ([request respond raise]
     (let [response (handler request (fn [response] (respond response)) raise)]
       (prn response)
       response))))

(defn wrap-prn-request
  ([handler]
   (wrap-prn-request handler {}))
  ([handler options]
   (fn
     ([request]
      (prn request)
      (handler request))
     ([request respond raise]
      (prn request)
      (handler request respond raise)))))

(def ^:private app
  (-> handler
      wrap-session
      wrap-json-response
      wrap-json-body
      wrap-params
      wrap-keyword-params
      wrap-flash
      wrap-prn-response
      wrap-prn-request))

(defn -main [& args]
  (println "Started main")
  (case (first args)
    "migrate" (migrations/migrate)
    (jetty/run-jetty app {:port 3000})))

;(-main)