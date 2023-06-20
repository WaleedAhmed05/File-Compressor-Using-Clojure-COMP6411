# File-Compressor-using-clojure
Text file compressor build completely on functional language Clojure. <br>
Assignment-02 COMP6411 - Comparative Study of Programming Languages @ Concordia University, Montreal.

### Short Summary
This file compressor can compress all ASCII characters text file and saves upto 30% storage.
Frequency.txt file has 10,000 most using english words. This program simply map word occurance position from frequency.txt file and replace words with digits. 

### Prerequisites
Download clojure from here [Clojure](https://clojure.org/guides/install_clojure) <br>
Make sure all files (menu.clj, compress.clj) are in same directory. <br>


### Commands
Run this command to start program.
```
clj menu.clj
```

![Option 1](https://github.com/WaleedAhmed05/File-Compressor-using-clojure/blob/main/Program_Options/option_1.png?raw=true)

Option 1 will Display all files in current directory. <br>


![Option 2](https://github.com/WaleedAhmed05/File-Compressor-using-clojure/blob/main/Program_Options/option_2.png?raw=true)

Option 2, will display file content.

![Option 3](https://github.com/WaleedAhmed05/File-Compressor-using-clojure/blob/main/Program_Options/option_3.png?raw=true)

Option 3, will compress selected text file and create a separate compress (.ct) file.

![Option 4](https://github.com/WaleedAhmed05/File-Compressor-using-clojure/blob/main/Program_Options/option_4.png?raw=true)

Option 4, will decompress selected compressed file and display decoded content on screen.








