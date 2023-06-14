(ns file-compressor.compress
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as strX])
  )


#_"compression Procedure."
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


;0.1 -if a word has punctuation attached.
(defn has-punctutions? [s]
  (not (nil? (re-find #"[,\(\)\[\]@\.?\$\!\-]" s)))
  )

;0.2
;This function will make space between words if they contains any punctuations, ex: man, -> man ,
(defn make-space-func [word]
  (clojure.string/split
    (str (clojure.string/replace
           (str (clojure.string/replace word #"[,\(\)\[\]@\.?\$\!\-]" #(str " " %1 " "))) #"\s{2,}" " "))
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


;0.4
(defn has-number? [string]
  (not (nil? (re-matches #"^\d+$" string)))
  )

(defn transform-input-file [words]
  (map #(cond
          (has-number? %) (str "@" % "@")                   ;this will check if a word is number?
          (has-punctutions? %) (string-processing-func %)   ;if there's any punctuation attached with word.
          (contains? wordsMap (strX/lower-case %)) (get wordsMap (strX/lower-case %)) ;this will simply return freq number from wordsMap
          :else (str %)
          )
       words)
  )

;(def output-file-name "output.txt.ct")

(defn save-words-to-file [words filename]                   ;store compressed words into output file.
  (spit filename (strX/join " " words)))

;(save-words-to-file store-compressed-results output-file-name)

(defn main-compress-function [file-name]
  (def store-compressed-resultX                             ;store compressed results.
    (transform-input-file (read-input-file file-name)))
  (save-words-to-file store-compressed-resultX (str file-name ".ct"))
  )



#_"Decompression Procedure."

;3.1
(defn read-compressed-file [file-name]
  (with-open [reader (io/reader file-name)]
    (let [lines (line-seq reader)
          words (->> lines
                     (mapcat #(clojure.string/split % #"\s+"))
                     (map #(clojure.string/replace % #"\s" "")))]
      words)))

;(def input-file-compressed-stream (read-compressed-file "output.txt.ct")) ;store input file words.



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




;This function will check if a string has two '@', if yes then it's a number.
(defn has-two-AtTheRate? [s]
  (>= (count (re-seq #"@" s)) 2))

(defn processed-number [word]
  (clojure.string/replace word #"[@]" (str ""))
  )


(defn capitalize-first [s]                                  ;Capitalize first letter of each sentence.
  (strX/replace-first s #"\b(\w)" #(str (clojure.string/upper-case (str (first %))))))

(defn add-space-after-punctuation [s]                       ;fix punctuations.
  (strX/replace s #"\s,|\s\.|\s\?|\s\!|\s]|\s\)" #(str "" (second %))))

(defn open-paranthesis [s]                                  ;fix punctuations.
  (strX/replace s #"\(\s|\@\s|\[\s|\$\s" #(str "" (first %))))


(defn main-decompress-function [file-name]


  (def decompressed-fileX

    (map #(cond
            (has-two-AtTheRate? %) (processed-number %)
            (has-alphabet? %) %                             ;if a word has an alphabet treat it as string.
            (has-number? %) (find-key-by-value wordsMap (Integer/parseInt %))
            (has-punctutions? %) (str %)
            :else (str "Invalid")
            )                                               ;else.
         (read-compressed-file file-name))                  ;from this input stream.
    )
  (def finaloutput
    (open-paranthesis
      (add-space-after-punctuation
        (capitalize-first (strX/join " " decompressed-fileX))
        ))
    )

  (println finaloutput)

  )





