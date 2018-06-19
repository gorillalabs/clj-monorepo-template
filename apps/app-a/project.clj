(defproject TEMPLATE-GROUP/TEMPLATE-APP "0.0.0"
  :description "A service to ... "
  :url "https://github.com/gorillalabs/clj-monorepo-template/"
  :license {:name "MIT"
            :url  "https://choosealicense.com/licenses/mit/"}

  :monolith/inherit true
  :middleware [leiningen.v/dependency-version-from-scm
               leiningen.v/version-from-scm
               leiningen.v/add-workspace-data]

  :plugins [[lein-monolith "1.0.1"]
            [com.roomkey/lein-v "6.2.0"]]

  :dependencies [[org.clojure/clojure nil] ;; use nil to inherit version from parent project
]

  :exclusions [org.slf4j/slf4j-log4j12
               ch.qos.logback/logback-classic]
  :uberjar-name "app.jar"
  :docker {:image-name "template/app"
           :tags       ["%s" "latest"]}
  :main nil
  :aliases {"install!" ^{:doc "Compile to uberjar, then build docker image."}
            ["do" ["clean"] ["test"] ["check"] ["uberjar"] ["docker" "build"]]}
  :profiles {:server      {:main app.core}
             :migrate     {:main app.db}
             :uberjar     {:main app.core
                           :aot  :all}
             :dev         [:project/dev :profiles/dev]
             :profiles/dev {}
             :project/dev {:source-paths ["dev"]
                           :dependencies [[org.clojure/tools.namespace "0.2.11"]]}}
  :env {:env "LOCAL"})
