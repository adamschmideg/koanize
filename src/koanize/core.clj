(ns koanize.core
  (require 
    [clojure.test :refer :all]))

(defn report2
  [m]
  (case (:type m)
    :fail (do (println "debug" *report-counters*) "just fail")
    :error (do (println m) (throw (Exception. (str "error" ))))
    nil))

(defn test-all-vars-until-successful
  "Calls test-var on every var interned in the namespace, with fixtures."
  {:added "1.1"}
  [ns]
  (let [once-fixture-fn (join-fixtures (::once-fixtures (meta ns)))
        each-fixture-fn (join-fixtures (::each-fixtures (meta ns)))]
    (once-fixture-fn
      (fn []
        (doseq [v (vals (ns-interns ns))]
          (when (and (:test (meta v))
                     (successful? @*report-counters*))
            (each-fixture-fn (fn [] (test-var v)))))))))

(defn meditate
  [& namespaces]
  (with-redefs [test-all-vars test-all-vars-until-successful]
    (try
      ;(apply require namespaces)
      (apply run-tests namespaces)
      (catch Exception e (println e)))))
