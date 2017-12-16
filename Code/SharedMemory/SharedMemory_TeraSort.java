import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class SharedMemory_TeraSort
{
	//initialize the variable
	public double start_time,end_time;
	long chunk_blockSize;	
	int no_Of_Threads;
	long fileSize;
	File file;	
	long freeMem;
	
	//Get the File to be sorted
	File getFile()
	{
		file = new File("inputfile.txt");
		return file;
	}
	//get te chunk size so that each file can be split into that size
	public void getChunkSize()
	{
		if(freeMem < fileSize)
		{
			chunk_blockSize = freeMem/100;	
		}
		else
		{
			chunk_blockSize = fileSize/1000;
		}
		System.out.println("Chunk Size ="+chunk_blockSize);

	}
	//print the free memory data and file size details
	void getFile_Memory_Details()
	{
		System.out.println("File Size in MB = "+(float)fileSize/(1000*1000));
		System.out.println("Free Memory in MB = "+(float)freeMem/(1000*1000));
		System.out.println("Number of Threads: "+ no_Of_Threads);			
	}
	void getThreadCount()
	{	
		Scanner scr = new Scanner(System.in);
		System.out.println("Enter number of threads");
		no_Of_Threads = scr.nextInt();
	}	
	public static void main(String args[]) throws Exception
	{		
		
		SharedMemory_TeraSort smt = new SharedMemory_TeraSort();
		smt.getThreadCount();
		File input_file = smt.getFile();
		if(!input_file.exists())
		{
			System.err.println("File does not exist");
			System.exit(1);
		}
		
		smt.fileSize = input_file.length();
		smt.freeMem = Runtime.getRuntime().freeMemory();
		smt.getFile_Memory_Details();
		//Get chunk size based
		smt.getChunkSize();
		//Calculate number of files to be created to sort the huge data
		long number_of_Files = (smt.fileSize/100)/smt.chunk_blockSize;
		System.out.println("Number of files to be created to sort = "+number_of_Files);
		
		SortFileChunk sfc = new SortFileChunk();
		//calculate the start time
		smt.start_time = System.currentTimeMillis();
		//call the function to divide the file into small chunk and sort them.
		int numSortFile = sfc.divideAndSort(input_file,smt.chunk_blockSize,smt.fileSize,smt.no_Of_Threads);
		//call the function to merge the files
		sfc.mergeSortedFiles(numSortFile,smt.fileSize);
		//calculate the endtime
		smt.end_time = System.currentTimeMillis();
		//compute the time
                double timediff = smt.end_time - smt.start_time;
		System.out.println("Total Time Taken to sort = "+timediff/1000+" sec");
		String time = "Total Time Taken to sort = "+timediff/1000+" sec";
		File file = new File("timetrack.txt");
		FileWriter fw = new FileWriter(file);
		fw.write(time);
		fw.close();
	}
}
