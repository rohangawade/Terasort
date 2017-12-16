
val start = System.currentTimeMillis()
//load input file to the hdfs system and get the keys which are first 10 characters of the line 
val file=sc.textFile("/data/input_file.txt")
val file_sort=file_input.map(line => (line.take(10), line.drop(10)))
/*sort the keys and store the line as per the keys by concatenating the value and save the data in output file*/
val sortkey = file_sort.sortByKey()
val keyval=sortkey.map {case (key,value) => s"$key $value"}
keyval.saveAsTextFile("/data/spark_output.txt")
val end = System.currentTimeMillis()
println ("Sorting time:-" + (end - start) + "ms")
