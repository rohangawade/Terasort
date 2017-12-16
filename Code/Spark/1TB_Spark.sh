

#!/bin/bash

time ./gensort -a 10000000000 inputfile.txt


./bin/spark-shell Spark_Sort.scala

./spark-ec2 -k PA2 -i /sparkpem.pem -s 1 -t i3.large --spot-price=0.665 launch spark_instance


time ./valsort output.txt
