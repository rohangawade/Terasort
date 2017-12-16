#!/bin/sh

echo 'Gensort for 1TB'
time ./gensort -a 10000000000 inputfile.txt

javac *.java
time java SharedMemory_TeraSort

echo 'Valsort for 128GB'
time ./valsort sharedmemory_terasort_output.txt

