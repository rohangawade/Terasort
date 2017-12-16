import java.io.IOException;
import java.util.ArrayList;

class MergeSort
{
	public static ArrayList<String> merge_Sort(ArrayList<String> dataList, long start, long end, int numThreads) throws Exception
	{	
		
		ArrayList<String> left = null;
		ArrayList<String> right =null;
		Thread thread=null;
		MergeSortThread sortleft=null;
		
		//finding mid value
		long totallen = start+end;
		long mid = totallen/2;
		
		//create and return empty arraylist if start is greater than end
		if(end<start)
		{
			ArrayList<String> newList = new ArrayList<String>();
			return newList;
		}
		if(start==end)
		{	
			ArrayList<String> one_ele;
			one_ele = new ArrayList<String>();
			String ele = dataList.get((int)start);
        		one_ele.add(ele);
			return one_ele;
		}
		//Sort Left
		
		if(numThreads<=1)
		{
			left = merge_Sort(dataList,start,mid,numThreads/2);		}
		else
		{
		//	System.out.println("Merge Sort left");
			//call Thread extended class
			sortleft = new MergeSortThread(dataList,start,mid,numThreads/2);
			thread = new Thread(sortleft);
			thread.start();
		
		}
		//System.out.println("Merge Sort right");
		int nthreads = numThreads - (numThreads/2);
		right = merge_Sort(dataList,mid+1,end,nthreads);

		if(numThreads>1)
		{
			try 
			{
				// join the threads
				thread.join();
			} 
			catch (InterruptedException e)
			{
				System.err.println("Thread joining failed");
			}
			int flag = 0;
			left = sortleft.getSortedData();
		}
		return mergeList(left,right);
	}

	public static boolean compareTwoListVal(String l,String r)
	{
		if(l.compareTo(r) <= 0)
			return true;
		else
			return false;
	}
	public static ArrayList<String> mergeList(ArrayList<String> leftlist, ArrayList<String> rightlist) throws Exception
	{
		ArrayList<String> sortedList = new ArrayList<String>();
		int left_pos =0, right_pos=0;
		
		long pos =0;
		long totallen = leftlist.size()+rightlist.size();
		//merging till totallen of original file fetched
		while(pos<totallen)
		{
			if (right_pos >= rightlist.size()) 
			{
			String ele = leftlist.get(left_pos);
                	sortedList.add(ele);
                	left_pos++;
            		}
            		else if (left_pos >= leftlist.size()) 
			{
				String ele = rightlist.get(right_pos);
                		sortedList.add(ele);
                		right_pos++;
            		}
            		else if (compareTwoListVal(leftlist.get(left_pos),rightlist.get(right_pos)))
			{
            			String ele = leftlist.get(left_pos);
				sortedList.add(ele);
                		left_pos++;
            		}
            		else 
			{
				String ele =rightlist.get(right_pos);
            			sortedList.add(ele);
                		right_pos++;
            		}
			pos++;
		}
		
		//System.out.println("Merge Sort");
		return sortedList;
	}
	private	static class MergeSortThread implements Runnable
	{
		ArrayList<String> unsortedList;
		ArrayList<String> sortedList;
		long start;
		long end;
		int numThreads;
		
		public MergeSortThread(ArrayList<String> unsortedData, long startind, long endind, int nThreads) 
		{
			// TODO Auto-generated constructor stub
			unsortedList = unsortedData;
			numThreads = nThreads;
			start = startind;
			end = endind;
		}
		
		public ArrayList<String> getSortedData()
		{
			return sortedList;
		}
		@Override
		public void run()
		{ 
			try
			{
			//call mergesort from run method
			sortedList = MergeSort.merge_Sort(unsortedList, start, end, numThreads);
			}
			catch(Exception e)
			{
			e.printStackTrace();			
			}		
		}
	
}
}


