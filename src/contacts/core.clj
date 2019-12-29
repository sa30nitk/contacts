(ns contacts.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]
            [contacts.handler :refer [handler]]))

(def app
  (-> handler
      wrap-session
      wrap-flash))

(defn -main [& args]
  (println "Started main")
  (case (first args)
    "migrate" (constantly (print "trying migration"))
    (jetty/run-jetty app {:port 3000})))

