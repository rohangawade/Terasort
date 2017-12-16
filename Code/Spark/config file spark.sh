
#!/bin/bash



sudo yum update

wget https://d3kbcqa49mib13.cloudfront.net/spark-2.2.0-bin-hadoop2.7.tgz

sudo tar zxvf spark-2.2.0-bin-hadoop2.7.tgz -C /opt

sudo ln -fs spark-2.2.0-bin-hadoop2.7 /opt/spark


sudo 
export export SPARK_HOME=/opt/spark
PATH=$PATH:$SPARK_HOME/bin export PATH



sudo spark-submit --version

cp $SPARK_HOME/conf/log4j.properties.template $SPARK_HOME/conf/log4j.properties
vi $SPARK_HOME/conf/log4j.properties

./$SPARK_HOME 







