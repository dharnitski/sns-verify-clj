(defproject sns-verify "0.0.1"
  :description "sample AWS SNS HTTP(S) endpoint that checks message validity"
  :url "https://github.com/dharnitski/sns-verify-clj"
  :min-lein-version "2.9.1"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.7.1"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler sns-verify.core/handler
         :port 8080})