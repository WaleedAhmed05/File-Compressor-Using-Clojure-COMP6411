(ns file-compressor.compress
  (:require [clojure.java.io :as io]))


(defn display_file_content []
   (let [file-path "core.clj"]
     (try                                                    ;try block to catch if file name doesn't exist.
       (with-open [reader (io/reader file-path)]
         (doseq [line (line-seq reader)]                       ;store each line of file in "line"
           (println line)))

       (catch java.io.FileNotFoundException e                  ;if file doesn't exist continue program.
         (println (str "File  does not exist. " file-path)))
       )

     ))

;(display_file_content)

(defn process-file [file-path]
  (with-open [reader (clojure.java.io/reader file-path)]
    (doseq [line (line-seq reader)]
      (doseq [word (clojure.string/split line #"\s+")]
        (println word)))))

(process-file "core.clj")

