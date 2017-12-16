import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

class SortFileChunk
{
	
	ArrayList<String> dataList = new ArrayList<String>();
	FileWriter fw;
	String chunk_file_name = "input_chunk";	
	//MergeSort ms=null;
	public int divideAndSort(File file,long chunk_FileSize,long fileSize,int numOfThreads) throws Exception
	{	
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		//ms = new MergeSort();
		//to track the total number of lines accessed
		long lineNum = 0;
		//to track the number of lines accessed in previous iteration to write one file
		long count = 0;
		while(true)
		{
			count = lineNum;
			String line ="";
			lineNum = count;
			//write to file block by block
			long totalLines = count + chunk_FileSize;
			while(lineNum<totalLines)
			{
				line = br.readLine();
				if(line == null)
					break;

				dataList.add(line);
					
				lineNum++;
			}
			if(line ==null)
			{
				if(dataList.isEmpty())
					break;
				else
					lineNum = lineNum+chunk_FileSize;
			}
			String filename = chunk_file_name + lineNum/chunk_FileSize + ".txt";
			fw = new FileWriter(new File(filename));
			
			//Sort the chunk file	
			//Thread.sleep(5000);
			System.out.println("number of blocks =" + ((fileSize/100)/chunk_FileSize));
			
			//System.out.println("call mergesort");
			ArrayList<String> sorted_data_List = MergeSort.merge_Sort(dataList,0,dataList.size()-1,numOfThreads);
			System.out.println("Creating sorted chunk file" + (lineNum/chunk_FileSize));
			
			
			for(String line_ele: sorted_data_List)
					fw.write(line_ele+" \n");
			
			dataList.clear();
			fw.close();
		}
		br.close();
		int sortedFiles = (int)(lineNum/chunk_FileSize);
		//System.exit(1);		
		return sortedFiles;
	}
	public boolean compareLine(String s1,String s2)
	{
		if(s1.compareTo(s2)<=0)
			return true;
		else
			return false;
				
	}

	public void mergeSortedFiles(int numFiles, long fileSize) throws Exception
	{
		long numFileLine[] =  new long[numFiles];
		BufferedReader br[] = new BufferedReader[numFiles];
		long mergeLength = 0L;
		String mergeArray[] = new String[numFiles];
		
		int file_num = 0 ;
		while(file_num < numFiles)
		{
			String filenm = chunk_file_name + (file_num+1)+".txt";
			br[file_num] =  new BufferedReader(new FileReader(new File(filenm)));
			numFileLine[file_num] = fileSize/100;
			if(numFileLine[file_num]>mergeLength)
				mergeLength = numFileLine[file_num];
			
			file_num++;
		}
		System.out.println("mergeLength = "+mergeLength);

		String val = "", small_val = "";
		int pos = -1,percentProgress =0 ;
		System.out.println("merge files");
		fw = new FileWriter(new File("output"+".txt"));
		int l = 0;
		while(l<fileSize/100)	
		{
			//int ele = 0;
			for(int ele=0;ele<numFiles;ele++)
			{
				if(mergeArray[ele] != null)	
				{
					val = mergeArray[ele];
										
					if((!val.equals("")) && (small_val.equals("")))
					{
						small_val =val;
					}
					if((!val.equals("")) && (compareLine(val,small_val)))
					{
						small_val = val;
						pos = ele;
					}

				}
				else
				{
					val = br[ele].readLine();
					if(val==null)
					{
						mergeArray[ele] = "";
						
					}
					else
					{
						if(!small_val.equals(""))
						{
						}
						else
						{
							small_val = val;
						}
						if(compareLine(val,small_val))
						{
							small_val = val;
							pos = ele;
						}
						mergeArray[ele]= val;
					}

				}
			}	
			
			mergeArray[pos]=null;			
			fw.write(small_val +"\n");
			small_val = "";
				
			if(l != 0)
			{		
					long remainingMB = fileSize/1000;
					if(l%remainingMB == 0)
					{
						percentProgress++;
						System.out.println("Merging: "+(percentProgress*10)+" percent");
					}
			}
		l++;		
		}
		System.out.println("100% merged");
		
		for(int i =1;i<=numFiles;i++)
		{
			br[i-1].close();
			new File("/mount1/"+chunk_file_name + i+".txt").delete();
		}
		fw.close();


	}
}

