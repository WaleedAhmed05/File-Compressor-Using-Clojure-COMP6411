(ns file-compressor.compress
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as strX]))


#_ "Decompression Procedure."
;1.0
;This method will read frequency.txt file and store all distinct words in a map.
(defn read-words-from-frequencytxt [file-name]
  (with-open [reader (io/reader file-name)]
    (let [lines (line-seq reader)
          ;words (map strX/lower-case (strX/split (strX/join " " lines) #"\s"))
          words (strX/split (strX/join " " lines) #"\s")
          unique-words (distinct words)                     ;store non duplicates only.
          word-frequencies (zipmap unique-words (range))]   ;store each word as "key" and value as it's occurance.
      word-frequencies)))

(def wordsMap (read-words-from-frequencytxt "frequency.txt")) ;store frequency map.

;2.0
;This method will read user input file to compress it's content.
(defn read-input-file [file-name]
  (with-open [reader (io/reader file-name)]
    (let [lines (line-seq reader)
          words (strX/split (strX/join " " lines) #"\s")
          ]
      words
      )))

(def input-file-words-stream (read-input-file "2.txt"))     ;store input file words.

(defn transform-input-file [words]
  (mapv #(get wordsMap (strX/lower-case %)) words))         ;compressing input file words by mapping to it's number.


(def store-compressed-results                               ;store compressed results.
  (transform-input-file input-file-words-stream))

(def output-file-name "output.txt")

(defn save-words-to-file [words filename]                   ;store compressed words into output file.
  (spit filename (strX/join " " words)))

(save-words-to-file store-compressed-results "output.txt.ct")



#_ "Decompression Procedure."
(defn find-key-by-value [mapX value]
(->> mapX
     (filter #(= value (val %)))
     (map key)
     (first)))

(println (find-key-by-value wordsMap 0))






