(defproject TEMPLATE-GROUP/TEMPLATE-LIB "0.0.0"
  :description "A library to provide core abstractions."
  :url "https://github.com/gorillalabs/clj-monorepo-template/"
  :license {:name "MIT"
            :url  "https://choosealicense.com/licenses/mit/"}

  :monolith/inherit true
  :middleware [leiningen.v/dependency-version-from-scm
               leiningen.v/version-from-scm
               leiningen.v/add-workspace-data]

  :plugins [[lein-monolith "1.0.1"]
            [com.roomkey/lein-v "6.2.0"]]

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.5"]

                 [com.taoensso/tufte "1.1.3"]
                 [com.taoensso/timbre "4.10.0"]

                 ;; Runtime dependencies to use timbre logging all over the place, even for java libraries
                 [com.fzakaria/slf4j-timbre "0.3.8"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]]
  :profiles {:dev         [:project/dev :profiles/dev]
             :profiles/dev {} :project/dev {:source-paths   ["dev"]
                                            :resource-paths ["dev-resources"]}}
  :exclusions [org.slf4j/slf4j-log4j12
               ch.qos.logback/logback-classic]
  :env {:env "LOCAL"})
