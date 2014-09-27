(ns koanize.meditate
  (require 
    [clojure.test :refer :all]))

(defmethod report :fail [m]
  (when (successful? @*report-counters*)
    (with-test-out
      (inc-report-counter :fail)
      (print "You are sitting on" (testing-vars-str m) "contemplating")
      (when (seq *testing-contexts*) (println (str " on \"" (testing-contexts-str) "\"")))
      ;(when-let [message (:message m)] (println "on" message))
      (println (str "Meditate on how `" (pr-str (:actual m)) "` will become `" (pr-str (:expected m)) "`")))))

(defmethod report :summary [m]
  (with-test-out
    (when-not (zero? (:pass m))
      ;(println "\nYou've already made" (:pass m) "steps...")
      )))

(defmethod report :begin-test-ns [m])

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
        (doseq [ns namespaces]
          (require ns :reload))
        (apply run-tests namespaces))
    nil)
