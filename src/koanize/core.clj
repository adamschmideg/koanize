(ns koanize.core
  (require 
    [clojure.test :refer :all]))

(defn report2
  [m]
  (case (:type m)
    :fail (throw (Exception. (str "failed" )))
    :error (do (println m) (throw (Exception. (str "error" ))))
    nil))

(defn meditate
  [& namespaces]
  (with-redefs [report report2]
    (try
      (apply run-tests namespaces)
      (catch Exception e (println e)))))
