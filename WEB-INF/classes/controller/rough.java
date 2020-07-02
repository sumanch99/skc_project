import java.util.*;
public class rough
{
	public static void main(String []args)
	{
		List<List> rows = new ArrayList<List>();
		int i=0;
	        while(i<3)
	        {
	        	List cols=new ArrayList<String>();
	        	cols.add("name"+i);
	        	cols.add("Code"+i);
	        	cols.add("Address"+i);
	        	cols.add("GST No"+i);
	        	rows.add(cols);
	        	i++;
	        } 
	    for(List<String> row:rows)
	    {
	    	for(String element : row)
	    	{
	    		System.out.print(element);
	    	}
	    }
      	
	}
}