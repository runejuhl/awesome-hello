(ns awesome-hello.core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]))

(defn handler
  [request]
    (println "got request from" (:remote-addr request) "for" (:uri request))
    (let [uri (:uri request)]
      (cond
        (= uri "/hello")
        {:status  200
         :headers {"Content-Type" "text/ascii"}
         :body    "    _    _   _
   / \\  | \\ | |
  / _ \\ |  \\| |
 / ___ \\| |\\  |
/_/   \\_\\_| \\_|
    ___        _______ ____   ___  __  __ _____
   / \\ \\      / / ____/ ___| / _ \\|  \\/  | ____|
  / _ \\ \\ /\\ / /|  _| \\___ \\| | | | |\\/| |  _|
 / ___ \\ V  V / | |___ ___) | |_| | |  | | |___
/_/   \\_\\_/\\_/  |_____|____/ \\___/|_|  |_|_____|
 _   _ _____ _     _     ___
| | | | ____| |   | |   / _ \\
| |_| |  _| | |   | |  | | | |
|  _  | |___| |___| |__| |_| |
|_| |_|_____|_____|_____\\___/
"}
        :else
        {:status  404
         :headers {"Content-Type" "text/html"}
         :body    "Nothing to see here, please move along."})))

(defn -main
  "Run an awesome server!"
  [& args]
  (run-jetty handler
             {:port  (or
                      (some->> (System/getenv "PORT")
                               (#(Integer. %)))
                      8089)
              :join? false}))
