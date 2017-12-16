import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

          
public class TeraSortHadoop {
	
	public static class HadoopMap extends Mapper<LongWritable, Text, Text, Text> {
	       
	       public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		  //
	    	  String val= value.toString();
	          String key = val.substring(0, 10);
	          String line=val.substring(10);
	          
	          Text keytext = new Text(key);
	          Text keyval = new Text(line);
	          
	          context.write(keytext,keyval);
      
	       }
	    }            
	    public static class HadoopReduce extends Reducer<Text, Text, Text, Text> {
	   
	       public void reduce(Text key, Iterable<Text> values, Context context) 
	         throws IOException, InterruptedException {

	    	   for (Text sum : values) {
	               context.write(key,sum);
	           }
	       }
	    }

	    public static void main(String[] args) throws Exception {
	        Configuration conf = new Configuration();
	        
		long start = System.currentTimeMillis();
	        
	        JobConf job = new JobConf(SortDriver.class);
		job.setJobName("Mapred");
	        job.setJarByClass(TeraSort.class);
	        job.setMapOutputKeyClass(Text.class);
	        job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
	            
	        job.setMapperClass(HadoopMap.class);
	        job.setReducerClass(HadoopReduce.class);

	            
	        job.setInputFormatClass(TextInputFormat.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	            
	        FileInputFormat.addInputPath(job, new Path(args[0]));
	        FileOutputFormat.setOutputPath(job, new Path(args[1]));
	            
	        job.waitForCompletion(true);
	        
	        
	        long end = System.currentTimeMillis();
		double timediff = (end - start)/1000;
		System.out.println("Total Time=    " + timediff +" seconds");
	     }

}
