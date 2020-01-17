(ns contacts.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.reload :refer [wrap-reload]]
            [contacts.routes :as routes]
            [contacts.db-migrations :as migrations]
            [contacts.config :as config]))

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
  (-> routes/handler
      wrap-session
      wrap-json-response
      wrap-json-body
      wrap-params
      wrap-keyword-params
      wrap-flash
      wrap-prn-request
      wrap-prn-response))

(defn start-service []
  (jetty/run-jetty (wrap-reload #'app) {:port config/app-port}))

(defn -main [& args]
  (case (first args)
    "migrate" (migrations/migrate)
    (start-service)))
