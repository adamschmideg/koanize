(ns koanize.sample-for-meditate
  (:require [clojure.test :refer :all]))

(deftest first-failure
  (testing "0 == 1" (is (= 0 1)))
  (testing "1 == 1" (is (= 1 1)))
  (testing "1 == 0" (is (= 1 0)))
  )

(deftest pass
  (testing "I am good"
    (is (= 1 1))))

(deftest second-failure
  (testing "you shouldn't see me"
    (is (= 0 2))))
