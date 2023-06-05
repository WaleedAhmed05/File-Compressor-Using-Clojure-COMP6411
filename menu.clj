#_( Author -Waleed Ahmed05
           Version - 1.0)

(ns file-compressor.menu
  (:require [clojure.java.io :as io]))

(defn main-menu []                                          ;Main menu to display options.
  (println "*** Compression Menu***
------------------------\n
1. Display list of files
2. Display file contents
3. Compress a file
4. Uncompress a file
5. Exit
  "))
(main-menu)

(defn get-user-input []
  (loop []
    (print "Enter an option: ")
    (flush)
    (let [input (read-line)
          num (Integer/parseInt input)]
      (if (and (integer? num) (< 0 num 5))                 ; check if user entered valid input
        num
        (recur)))))

(def user-input (get-user-input))


(defn display_list_of_files []
  (println "File List: ")
  (let [current-dir (io/file ".")]
    (doseq [file (file-seq current-dir)
            :when (.isFile file)
            :let [filename (.getName file)]]
      (println "* ./"filename))))

  ;(let [current-dir (io/file ".")]
  ;  (doseq [file (file-seq current-dir)
  ;          :when (.isFile file)
  ;          :let [filename (.getName file)]]
  ;    (when (.endsWith filename ".txt")
  ;      (println filename)))))




(defn display_file_content [])                             ;TODO
(defn compress_file [])                             ;TODO
(defn decompress_file [])                             ;TODO
(defn exit [])                             ;TODO


(defn menu-options [n]
  (case n
    1 (display_list_of_files)                               ;Display list of files.
    2 (display_file_content)                                ;Display content of a selected file.
    3 (compress_file)                                       ;Compress file
    4 (decompress_file)                                     ;UnCompress file back to original
    5 (exit)                                                ;Close program.
    (println "Invalid Argument")
    )
  )

(menu-options 1)






