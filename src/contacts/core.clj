(ns contacts.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]
            [contacts.handler :refer [handler]]))

(def app
  (-> handler
      wrap-session
      wrap-flash))

(defn -main []
  (println "Started main")
  (jetty/run-jetty app {:port 3000}))

