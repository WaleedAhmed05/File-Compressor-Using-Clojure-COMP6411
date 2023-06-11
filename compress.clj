(ns file-compressor.compress
  (:require [clojure.java.io :as io]))


(defn read-frequenciesx [file-name]
  (with-open [reader (io/reader file-name)]
    (let [lines (line-seq reader)
          ;words (map clojure.string/lower-case (clojure.string/split (clojure.string/join " " lines) #"\s"))
          words (clojure.string/split (clojure.string/join " " lines) #"\s")
          unique-words (distinct words)
          word-frequencies (zipmap unique-words (range))]
      word-frequencies)))

;(println (count (read-frequencies "frequency.txt")) )
(def xVar (read-frequenciesx "frequency.txt"))
;(println (count (read-frequenciesx "frequency.txt")))

;The pink elephant is absolutely groovy
;The first man is from the last group of people

(println (get xVar "the")
         (get xVar "first")
         (get xVar "man")
         (get xVar "is")
         (get xVar "from")
         (get xVar "the")
         (get xVar "last")
         (get xVar "group")
         (get xVar "of")
         (get xVar "people")
         )




