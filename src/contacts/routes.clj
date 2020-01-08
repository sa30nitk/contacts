(ns contacts.routes
  (:require [contacts.handlers.ping :as ping]
            [bidi.ring :refer [make-handler]]
            [contacts.handlers.contact :as contact]))

(def ^:private internal-routes ["v1/" {"contacts" {:get  {""        contact/getContactsHandler
                                                          ["/" :id] contact/getContact}
                                                   :post contact/createContactHandler}}])
(def ^:private routes ["/" [["ping" ping/pingHandler]
                            internal-routes
                            [true (constantly {:status 404})]]])
(def handler
  (make-handler routes))
