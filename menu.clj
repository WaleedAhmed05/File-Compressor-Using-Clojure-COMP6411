#_( Author -Waleed Ahmed05
           Version - 1.0)

(ns file-compressor.menu)

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
;(println "Hello, " user-input "!")




