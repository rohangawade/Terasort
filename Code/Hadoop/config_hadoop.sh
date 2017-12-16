
#!/bin/bash



sudo wget http://apache.claz.org/hadoop/common/hadoop-2.7.4/hadoop- 2.7.4.tar.gz
tar -xzvf hadoop-2.7.4.tar.gz
tar -xzvf gensort-linux-1.5.tar.gz
chmod 777 hadoop-2.7.4
sudo apt-get install ssh

eval $(ssh-agent)
ssh-add /home/ubuntu/hadooppem.pem

chmod 400 hadooppem.pem

sudo apt-get install default-jdk

sudo apt-get update
sudo apt-get upgrade

sudo 
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export HADOOP_HOME=/usr/local/hadoop
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib"
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar


sudo apt-get install ssh
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
cat ~/.ssh/id_ds.pub >> ~/.ssh/authorized_keys
cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys

sudo mv hadoop-2.7.4 /usr/local/hadoop
 
sudo apt-get install gcc

sudo apt-get install openjdk-7-jdk

update-alternatives --config java


mkdir -p /usr/local/hadoop/hadoop_data/hdfs/namenode

mkdir -p /usr/local/hadoop/hadoop_data/hdfs/datanode

sudo chown ubuntu:ubuntu -R /usr/local/hadoop

hdfs namenode -format

./start-all.sh

hadoop fs -put TeraSort_Hadoop  /Input

hadoop fs -get /Output TeraSort_Hadoop





