(ns koanize.meditate-test
  (:require [clojure.test :refer :all]
            [koanize.meditate :refer :all]
            ))

(deftest meditate-test
  (testing "fail"
    (let [output
          (with-out-str
            (binding [*test-out* *out*]
              (meditate 'koanize.sample-for-meditate)))]
      (is (.startsWith output "You are sitting"))
      ;(is (.endsWith output "Meditate on how "))
      )))

(run-tests)
