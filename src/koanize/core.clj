(ns koanize.core
  (require 
    [clojure.test :refer :all]))

(defn report2
  [m]
  (case (:type m)
    :fail (throw (Exception."failed" m))
    :error (throw (Exception. "error" m))
    nil))

(defn meditate
  []
  (with-redefs [report report2]
    (try
      (run-tests)
      (catch Exception e (println e)))))
