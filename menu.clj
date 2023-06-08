#_( Author -Waleed Ahmed05
           Version - 1.0)

;0.1
(ns file-compressor.menu
  (:require [clojure.java.io :as io]))


;0.0
(defn get-user-input []                                     ;user input for menu [ 1 to 5]
  (loop []
    (print "Enter an option: ")
    (flush)
    (let [input (read-line)
          num (Integer/parseInt input)]
      (if (and (integer? num) (< 0 num 5))                 ; check if user entered valid input
        num
        (recur)))))

;0.1
(defn main-menu []                                          ;Main menu to display options.
  (println "*** Compression Menu***
------------------------\n
1. Display list of files
2. Display file contents
3. Compress a file
4. Uncompress a file
5. Exit
  ")

  )
(main-menu)
(def user-input (get-user-input))




(defn trigger-user-input []
  (user-input)
  )

;1.0
(defn display_list_of_files []
  (println "File List: ")
  (let [current-dir (io/file ".")]
    (doseq [file (file-seq current-dir)
            :when (.isFile file)
            :let [filename (.getName file)]]
      (println "* ./"filename)))
  ;(main-menu)
  )

;2.1
(defn take-file-name-input []                               ;Take filename from user.
  (print "Enter File Name: ")                               ;TODO put file name validations here.
  (flush)
  (let [input (read-line)]
    input)
  )

;2.0
(defn display_file_content []
  (let [file-path (take-file-name-input)]
    (try                                                    ;try block to catch if file name doesn't exist.
    (with-open [reader (io/reader file-path)]
      (doseq [line (line-seq reader)]                       ;store each line of file in "line"
        (println line)))

    (catch java.io.FileNotFoundException e                  ;if file doesn't exist continue program.
      (println (str "File  does not exist. " file-path)))
    )

    ))


(defn compress_file [])                             ;TODO - To be implement
(defn decompress_file [])                           ;TODO - To be implement
(defn exit [])                                      ;TODO - To be implement

;0.3
(defn menu-options [n]
  (case n
    1 ((display_list_of_files) (main-menu) (menu-options (get-user-input) ))                                ;Display list of files.
    2 ((display_file_content) (main-menu) (menu-options (get-user-input) ))                                ;Display content of a selected file.
    3 (compress_file)                                       ;Compress file
    4 (decompress_file)                                     ;UnCompress file back to original
    5 (exit)                                                ;Close program.
    (println "Invalid Argument")
    )
  )

(menu-options user-input)






