(defproject awesome-hello "0.1.0"
  :description "awesome hello!"
  :url "https://github.com/runejuh/awesome-hello"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-devel "1.9.4"]
                 [ring/ring-jetty-adapter "1.9.4"]]
  :plugins [[lein-ring "0.12.5"]]
  :main ^:skip-aot awesome-hello.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :uberjar-name "awesome-hello-standalone.jar")
