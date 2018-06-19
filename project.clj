;;;
;;; ######################################################################
;;; ## check, test and install
;;;
;;;     lein monolith each do clean, check, test, install
;;;
;;;
;;; ## generate documentation
;;;
;;;     lein monolith each do install, codox
;;;
;;;
;;; ## release using a parameter to "lein v update"
;;;
;;;   lein release minor
;;;   lein release major
;;;
;;; ######################################################################


(defproject TEMPLATE-GROUP/TEMPLATE-GROUP "0.0.0"
  ;; we use "0.0.0" as version, as the version is derived from git tags (`vMAJOR.MINOR.PATCH` by lein-v.)
  ;; using anything else but the usual "string-with-numbers" might throw off other tools like your IDE.
  :description "TEMPLATE - Parent project."
  :url "https://github.com/gorillalabs/clj-monorepo-template/"
  :license {:name "MIT"
            :url  "https://choosealicense.com/licenses/mit/"}
  :vcs :git
  :plugins [;; these dependencies are not necessary but conflict
            ;; resolutions
            [org.clojure/clojure "1.9.0"]
            [org.apache.httpcomponents/httpclient "4.5.5"]
            [org.apache.maven.wagon/wagon-provider-api "2.12"]
            [org.apache.maven.wagon/wagon-http "2.12"]
            [org.clojure/tools.reader "1.2.1"]
            [rewrite-clj "0.5.2"]
            [org.clojure/tools.cli "0.3.5"]

            ;; essential for the project structure, as we depend on inheritance of project.clj entries
            [lein-monolith "1.0.1"]
            [com.roomkey/lein-v "6.2.0"]

            ;; test and documentation
            [lein-codox "0.9.4"]
            [lein-marginalia "0.9.0"]

            [jonase/eastwood "0.2.6-beta2"]
            [lein-ancient "0.6.15"]
            [lein-kibit "0.1.5"]

            ;; build and run
            [lein-environ "1.1.0"]
            [gorillalabs/lein-docker "1.5.0"]

            ;; little helpers
            [lein-cljfmt "0.5.7"]
            [lein-cprint "1.2.0"]]

  :middleware [leiningen.v/version-from-scm
               leiningen.v/add-workspace-data]

  :pedantic? :abort
  :dependencies [[org.clojure/clojure "1.9.0"]

                 ;; Complete instrumentation for clojure.spec
                 ;; https://clojars.org/orchestra
                 [orchestra "2017.11.12-1"]

                 ;; Human-optimized error messages for clojure.spec
                 ;; https://clojars.org/expound
                 [expound "0.4.0"]

                 ;; Runtime dependencies to use timbre logging all over the place, even for java libraries
                 [com.fzakaria/slf4j-timbre "0.3.8"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]]

  :exclusions [org.clojure/clojure
               org.slf4j/slf4j-nop
               org.slf4j/slf4j-log4j12
               log4j
               commons-logging/commons-logging]

  :codox {:metadata {:doc/format :markdown}}

  :monolith {:inherit       [:url
                             :license
                             :test-selectors
                             :env
                             :plugins
                             :profiles
                             :middleware
                             :codox
                             :eastwood
                             :repl-options
                             :exclusions
                             :local-repo]

             :inherit-leaky [:dependencies
                             :repositories
                             :deploy-repositories
                             :managed-dependencies]

             :project-dirs  ["apps/*" "libs/*"]}

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ;; Releasing stuff
  ;;
  :scm {:name "git"
        :url  "https://github.com/gorillalabs/clj-monorepo-template/"}

  :release-tasks [["vcs" "assert-committed"]
                  ["v" "update"] ;; compute new version & tag it
                  ["monolith" "each" "deploy"]
                  ["vcs" "push"]]

  :profiles {:install-for-with-all-repl {:middleware ^:replace [leiningen.v/dependency-version-from-scm]}
             :dev                       [:project/dev :profiles/dev]
             :profiles/dev              {}
             :project/dev               {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                                                        ;; https://clojars.org/expectations
                                                        [expectations "2.2.0-rc3"]]}})
