package square;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LongestRepeatedSubString 
{
	public String getLongestRepeatedSubstring(String str)
	{
		char[] strarray=str.toCharArray();
		int[][] memoarray=new int[strarray.length+1][strarray.length+1];
		int maxRep=Integer.MIN_VALUE;
		int index=-1;

		for(int i=1;i<=strarray.length;++i)
		{
			for(int j=1;j<=strarray.length;j++)
			{
				if(strarray[i-1]==strarray[j-1] && Math.abs(i-j)>memoarray[i-1][j-1])
				{
					memoarray[i][j]=memoarray[i-1][j-1]+1;
					if(memoarray[i][j]>maxRep)
					{
						maxRep=memoarray[i][j];
						index=Math.min(i, j);
					}
				}
				else
				{
					memoarray[i][j]=0;
				}
			}
		}
		
		if(maxRep>0)
		{
			String result=str.substring(index-maxRep, index);
			for(int i=0;i<result.length();i++)
			{
				if(result.charAt(i)!=' ')
				{
					return result;
				}
			}
		}
		return null;
	}

	public static void main(String args[])
	{
		String filepath=args[0];
        BufferedReader reader=null;
		try 
		{
			reader = new BufferedReader(new FileReader(filepath));
            String line = null;
            LongestRepeatedSubString lrss= new LongestRepeatedSubString();
            while ((line = reader.readLine()) != null) 
            {
            	System.out.println(lrss.getLongestRepeatedSubstring(line));
            }
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("file not found");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("Reader not found");
			e.printStackTrace();
		}
	
	}
}
