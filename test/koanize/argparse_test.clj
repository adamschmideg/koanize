(ns koanize.argparse-test
  (:require
    [clojure.test :refer :all]
    [koanize.argparse :refer :all]))

(deftest group-by-count-test
  (testing "group-by-count-test"
    (is (= (group-by-count 2 [:a :b :c :d])
           [[:a :b] [:c :d]]))
    (is (= (group-by-count 1 [:a :b :c])
           [[:a] [:b] [:c]]))
    (is (= (group-by-count 2 [:a :b :c])
           [[:a :b]])
        "Rest is discarded")
    (is (= (group-by-count 9 [:a :b :c])
           [])
        "Empty if count is more than the length of the collection")))

(testing "group-by-counts-test"
  (is (= (group-by-counts [2 2] [:a :b :c :d])
         [[:a :b] [:c :d]]))
  (is (= (group-by-counts [1 2] [:a :b :c])
         [[:a] [:b :c]])
      "Different counts")
  (is (= (group-by-counts [2 3] [:a :b :c])
         [[:a :b]])
      "Rest is discarded")
  (is (= (group-by-counts [9 1] [:a :b :c])
         [])
      "Empty if count is more than the length of the collection"))

(testing "chunks"
  (is (= (make-chunks [2 :a :b 1 :c 3 :d :e :f])
         [[2 :a :b] [1 :c] [3 :d :e :f]])))

(testing "chunks by keys"
  (is (= (chunks-by-key {:ONE 1, :TWO 2}
                        [:TWO :a :b :ONE :c :THREE :d :e :f])
         {:ONE [:c],
          :TWO [:a :b],
          :THREE [:d :e :f]})))

(testing "argparse"
  (is (= (argparse [{:short "-a", :count 0},
                    {:short "-b", :count 0},
                    {:short "-c", :count 1}]
                   ["-a" "-c" "foo" "-b"])
         {:good {"-a" [],
                 "-b" [],
                 "-c" ["foo"]}})))

;(run-tests)
