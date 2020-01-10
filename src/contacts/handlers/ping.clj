(ns contacts.handlers.ping
  (:require [ring.util.response :as res]))

(defn pingHandler
  [request]
  (res/response "pong"))