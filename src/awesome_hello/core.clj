(ns awesome-hello.core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]))
(defonce numbers
  {0 "  ___  \n / _ \\ \n| | | |\n| |_| |\n \\___/ \n       \n",
   7 " _____ \n|___  |\n   / / \n  / /  \n /_/   \n       \n",
   1 " _ \n/ |\n| |\n| |\n|_|\n   \n",
   4 " _  _   \n| || |  \n| || |_ \n|__   _|\n   |_|  \n        \n",
   6 "  __   \n / /_  \n| '_ \\ \n| (_) |\n \\___/ \n       \n",
   3 " _____ \n|___ / \n  |_ \\ \n ___) |\n|____/ \n       \n",
   2 " ____  \n|___ \\ \n  __) |\n / __/ \n|_____|\n       \n",
   9 "  ___  \n / _ \\ \n| (_) |\n \\__, |\n   /_/ \n       \n",
   5 " ____  \n| ___| \n|___ \\ \n ___) |\n|____/ \n       \n",
   8 "  ___  \n ( _ ) \n / _ \\ \n| (_) |\n \\___/ \n       \n"})

(defonce visit-counter (atom 0))

(defn handler
  [request]
  (println "got request from" (:remote-addr request) "for" (:uri request))
  (flush)
  (let [uri (:uri request)]
      (cond
        (= uri "/health")
        {:status 200
         :body   "ok"}
        (= uri "/hello")
        {:status  200
         :headers {"Content-Type" "text/plain"}
         :body    (clojure.string/join \newline
                                       ["    _    _   _
   / \\  | \\ | |
  / _ \\ |  \\| |
 / ___ \\| |\\  |
/_/   \\_\\_| \\_|"
                                        "    ___        _______ ____   ___  __  __ _____
   / \\ \\      / / ____/ ___| / _ \\|  \\/  | ____|
  / _ \\ \\ /\\ / /|  _| \\___ \\| | | | |\\/| |  _|
 / ___ \\ V  V / | |___ ___) | |_| | |  | | |___
/_/   \\_\\_/\\_/  |_____|____/ \\___/|_|  |_|_____|"
                                        " _   _ _____ _     _     ___
| | | | ____| |   | |   / _ \\
| |_| |  _| | |   | |  | | | |
|  _  | |___| |___| |__| |_| |
|_| |_|_____|_____|_____\\___/
"
                                        " _____ ___
|_   _/ _ \\
  | || | | |
  | || |_| |
  |_| \\___/

"
                                        "__     _____ ____ ___ _____ ___  ____
\\ \\   / /_ _/ ___|_ _|_   _/ _ \\|  _ \\
 \\ \\ / / | |\\___ \\| |  | || | | | |_) |
  \\ V /  | | ___) | |  | || |_| |  _ <
   \\_/  |___|____/___| |_| \\___/|_| \\_\\

"
                                        "   _  _
 _| || |_
|_  ..  _|
|_      _|
  |_||_|

"
                                        (map (comp (partial get numbers) #(Integer. (str %)))
                                             (str (first (swap-vals! visit-counter inc))))])
         }
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
