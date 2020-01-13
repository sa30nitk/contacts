(ns contacts.routes
  (:require [contacts.handlers.ping :as ping]
            [bidi.ring :refer [make-handler]]
            [contacts.handlers.contact :as contact]))

(def ^:private internal-routes ["v1/" {"contacts" {:get    {""        contact/get-contacts-handler
                                                            ["/" :id] contact/get-contact-handler}
                                                   :post   contact/create-contact-handler
                                                   :patch  contact/patch-contact-handler
                                                   :delete {["/" :id] contact/delete-contact-handler}}}])
(def ^:private routes ["/" [["ping" ping/pingHandler]
                            internal-routes
                            [true (constantly {:status 404})]]])
(def handler
  (make-handler routes))