(ns koanize.core-test
  (:require [clojure.test :refer :all]
            [koanize.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest good-test
  (testing "I am good"
    (is (= 1 1))))

(deftest b-test
  (testing "you shouldn't see me"
    (is (= 0 1))))

(meditate)
