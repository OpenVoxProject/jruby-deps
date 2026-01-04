(def jruby-version "9.4.12.1")

(defproject org.openvoxproject/jruby-deps "9.4.12.1-1-SNAPSHOT"
  :description "JRuby dependencies"
  :url "https://github.com/openvoxproject/jruby-deps"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}

  :parent-project {:coords [org.openvoxproject/clj-parent "7.6.4"]
                   :inherit [:managed-dependencies]}

  :min-lein-version "2.7.1"

  :pedantic? :abort

  :dependencies [[org.jruby/jruby-base ~jruby-version :exclusions [joda-time]]
                 [org.jruby/jruby-stdlib ~jruby-version]
                 [org.snakeyaml/snakeyaml-engine "2.7"]]

  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/CLOJARS_USERNAME
                                     :password :env/CLOJARS_PASSWORD
                                     :sign-releases false}]]

  :plugins [[lein-release-4digit-version "0.2.0"]]

  ;; EZbake relies on this being stored top-level in the jar,
  ;; when including this project as an additional uberjar when building
  ;; pe-puppetserver. Lein 2.8.0 stopped adding project.clj at the top level.
  ;; Long term, it might be better to fix ezbake to handle the file's new location
  ;; under `META-INF/leiningen/group/artifact/project.clj`, but since this is the
  ;; only project that relies on that right now, it's simpler to just add it here.
  :resource-paths ["project.clj"]

  :uberjar-name "jruby-9k.jar")
