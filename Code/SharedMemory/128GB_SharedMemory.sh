#!/bin/sh

echo 'Gensort for 128GB'
time ./gensort -a 1280000000 inputfile.txt

javac *.java
time java SharedMemory_TeraSort

echo 'Valsort for 128GB'
time ./valsort sharedmemory_128GB_output.txt

