
#!/bin/bash

time ./gensort -a 1280000000 inputfile.txt

hadoop com.sun.tools.javac.Main TeraSort_Hadoop.java

jar	cf hp.jar *.class

hadoop jar	hp.jar	TeraSort_Hadoop input output

hdfs dfs –get output

time ./valsort output.txt

