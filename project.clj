(def jruby-version "9.4.15.0")

(defproject org.openvoxproject/jruby-deps "10.0.6.0-1-SNAPSHOT"
  :description "JRuby dependencies"
  :url "https://github.com/openvoxproject/jruby-deps"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}

  :min-lein-version "2.7.1"

  :pedantic? :abort

  :dependencies [[org.jruby/jruby-base ~jruby-version]
                 [org.jruby/jruby-stdlib ~jruby-version]
                 [org.snakeyaml/snakeyaml-engine "2.10"]]

  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/CLOJARS_USERNAME
                                     :password :env/CLOJARS_PASSWORD
                                     :sign-releases false}]]

  :plugins [[lein-release-4digit-version "0.2.0"]])
