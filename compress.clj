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

(def input-file-words-stream (read-input-file "3.txt"))     ;store input file words.

;(defn transform-input-file [words]
;  (mapv #(get wordsMap (strX/lower-case %)) words))         ;compressing input file words by mapping to it's number.

;(defn transform-input-file [words]
;  (mapv #(or (get wordsMap (strX/lower-case %)) %) words))

;0.1 -if a word has punctuation attached.
(defn has-punctutions? [s]
  (not (nil? (re-find #"[,\(\)\[\]@\.?\$\!\-]" s)))
  )

;0.2
;This function will make space between words if they contains any punctuations, ex: man, -> man ,
(defn make-space-func [word]
  (clojure.string/split
    (str (clojure.string/replace
           (str (clojure.string/replace word  #"[,\(\)\[\]@\.?\$\!\-]" #(str " " %1 " "))) #"\s{2,}" " "))
    #"\s+")
  )

;0.3
;This function will split punctuation words then combine them in a single string.
(defn string-processing-func [word]
  (def text "")
  (
    doseq [x (make-space-func word)]
    (if (contains? wordsMap (strX/lower-case x))
      (def text (str text " " (get wordsMap (strX/lower-case x))))
      (def text (str text " " x))
      )
    )
  text
  )

;TODO do not delete.
;0.4
;This function will check if a string has two '@', if yes then it's a number.
;(defn has-numberX? [s]
;  (>= (count (re-seq #"@" s)) 2))
;
;;0.5
;(defn processed-number [word]
;  (clojure.string/replace word #"[@]" #(str " " %1 " "))
;  )

;0.4
(defn has-number? [string]
  (not (nil? (re-matches #"^\d+$" string)))
  )

(defn transform-input-file [words]
  (map #(cond
          (has-number? %) (str "@"%"@")
          (has-punctutions? %) (string-processing-func %)
          (contains? wordsMap (strX/lower-case %)) (get wordsMap (strX/lower-case %)) ;this will simply return freq number from wordsMap
          :else (str "this is else.")

          )
       words)
  )



(def store-compressed-results                               ;store compressed results.
  (transform-input-file input-file-words-stream))

(doseq [x store-compressed-results]
      (print x " ")
      )






(def output-file-name "output.txt.ct")

(defn save-words-to-file [words filename]                   ;store compressed words into output file.
  (spit filename (strX/join " " words)))

(save-words-to-file store-compressed-results output-file-name)



#_ "Decompression Procedure."

;3.1
;This method will read compressed file to decompress it's content.
(defn read-compressed-file [file-name]
  (with-open [reader (io/reader file-name)]
    (let [lines (line-seq reader)
          words (strX/split (strX/join " " lines) #"\s")
          ]
      words
      )))

(def input-file-compressed-stream (read-compressed-file "output.txt.ct"))     ;store input file words.

;3.2
;This function will map compressed value with real string.
(defn find-key-by-value [mapX value]
(->> mapX
     (filter #(= value (val %)))
     (map key)
     (first)))

;3.3.1
;This function will check if a word contains any alphabet?
;It will consider a word as string if atleast 1 of word's character is alaphabet.
(defn has-alphabet? [s]
  (not (nil? (re-find #"[a-zA-Z]" s))))

(defn has-atTheRate [s]
  (not (nil? (re-find #"[@]" s)))
  )

;3.3

(def decompressed-text

  (map #(cond
          (has-alphabet? %) %                         ;if a word has an alphabet treat it as string.
          (has-atTheRate %) (str "this is a number")
          :else (str "do-something-else")
           )                         ;else.
       input-file-compressed-stream)                        ;from this input stream.

  )

;


;(doseq [x decompressed-text]
;    (println x)
;    )




