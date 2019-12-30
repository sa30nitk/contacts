(ns contacts.routes
  (:require [contacts.handlers.ping :as ping]
            [bidi.ring :refer [make-handler]]
            [contacts.handlers.contact :as contact]))

(def ^:private internal-routes ["" {["contact/" :id] {:post contact/contactDetailsHandler}}])

(def ^:private routes ["/" [["ping" ping/pingHandler]
                            internal-routes
                            [true (constantly {:status 404})]]])
(def handler
  (make-handler routes))
