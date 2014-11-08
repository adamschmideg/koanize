(ns koanize.argparse)

(defn group-by-count
  [n xs]
  (when-let [head (seq (take n xs))]
    (if (= n (count head))
      (let [partial-result (group-by-count n (drop n xs))]
        (cons head partial-result))
      [])))

(defn group-by-counts
  [counts xs])

(defn make-chunks
  [coll])

(defn chunks-by-key
  [counts xs])

(defn argparse
  [spec args])