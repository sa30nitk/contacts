(defproject contacts "0.1.0-SNAPSHOT"
  :description "Contacts: An application for storing contacts"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-devel "1.6.3"]
                 [bidi "2.1.6"]

                 ;; log4j is only for dependencies.
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.11.2"]

                 ;; Database migrations
                 [ragtime "0.8.0"]
                 [org.clojure/java.jdbc "0.7.9"]
                 [org.postgresql/postgresql "42.2.5"]]
  :main contacts.core
  :target-path "target/%s"
  :plugins [[lein-cljfmt "0.6.4"]
            [lein-cloverage "1.0.13"]
            [jonase/eastwood "0.3.5"]]
  :profiles {:uberjar {:aot :all}})
