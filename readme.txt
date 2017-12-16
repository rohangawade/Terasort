SHARED_MEMORY


Files:
SharedMemory_TeraSort.java
This files finds the free memory and chunksize and calls S
SortFileChunk.java
Contains method to divide and sort the file and then merge the file into single file. The sorting is performed as per the mergesort
MergeSort.java
Contains the logic for mergesort and merge function.


Steps for compile and execute the Shared-Memory on virtual cluster of 1 node.

Steps:
	1. Launch Amazon AWS i3.large and i3.4xlarge  and i3.4xlarge for 1TB instance and attach the reqiured volume to the instance by using 
		./mountRAID.sh
		
	2. To connect to the cluster use the command 
		ssh -i "sharepem.pem" ubuntu@ec2-18-216-212-65.us-east-2.compute.amazonaws.com
		
	3. To copy the file and bash command  from local host use the scp command
		scp -i  "hadooppem.pem" ~/128GB_SharedMemory.java  ubuntu@ec2-18-216-212-65.us-east-2.compute.amazonaws.com:~/
	
	4. This will load the file from local instance to the master node
	
	5. Run the Shared_Memory_128GB.sh file to execute the the gensort, run the program
		./128GB_Shared_Memory.sh
		
	6. The output file is created and the time required to run the file is store in it

		
		
------------------------------------------------------------------------------------------------------------------------------------------------------		
Hadoop:

Files:
TeraSort.java
This file contains the logic to take the file as input and load into hadoop system and use Mapper and Reducer to sort the file.

Steps for compile and execute the Hadoop on virtual cluster of 1 node.
Steps:
	1. Launch Amazon AWS i3.large for 128GB and i3.4xlarge for 1TB instance  and attach the reqiured volume to the instance by using 
		./mountRAID.sh
		
	2. To connect to the cluster use the command 
		ssh -i "hadooppem.pem" ubuntu@ec2-18-216-212-139.us-east-2.compute.amazonaws.com
	
	3. To copy the file from local host use the scp command
		scp -i  "hadooppem.pem" ~/128GB_Hadoop.java  ubuntu@ec2-18-216-212-65.us-east-2.compute.amazonaws.com:~/
		
	
	4. Install the reqiured configuration for hadoop by
		./config file hadoop.sh
	
	5. Configured the reqiured xml files like core-site.xml,hdfs-site.xml,mapred-site.xml,yarn-site.xml
			
	6. Run the bash file for  executing gensort,compiling ,creating jar and running the jar file
		./128GB_Hadoop.sh
			
	7. The output file is created and the time required to run the file is store in it

-------------------------------------------------------------------------------------------------------------------------------------------------------------------		
SPARK

Files:
Spark_Sort.scala
It contains the logic to sort the file by using the key and then concatenating the value to the key and returning output as sorted file.


Steps for compile and execute the Spark on virtual cluster of 1 node.
Steps:
	1. Launch Amazon AWS i3.large for 128GB and i3.4xlarge for 1TB instance  and attach the reqiured volume to the instance by using 
		./mountRAID.sh
		
	2. To connect to the cluster use the command 
		ssh -i "hadooppem.pem" ubuntu@ec2-18-216-212-139.us-east-2.compute.amazonaws.com
	
	3. To copy the file from local host use the scp command
		scp -i  "sparkpem.pem" ~/128GB_Hadoop.java  ubuntu@ec2-18-216-212-139.us-east-2.compute.amazonaws.com:~/
		
	4. Configure and install hadoop before using Spark for that see the hadoop section 	
		
	
	5. Install the reqiured configuration for spark by
		./config file spark.sh
				
	6. Run the bash file for  executing gensort,compiling and executing spark file
		./128GB_Spark.sh
			
	7. The output file is created and the time required to run the file is store in it


