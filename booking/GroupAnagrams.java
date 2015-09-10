package booking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class GroupAnagrams 
{
	public static void  group_all_anagrams(ArrayList<String> str_list)
	{
		HashMap<String,ArrayList<String>> str_map = new HashMap<>();
		for (String str : str_list)
		{
			char[] char_array= str.toCharArray();
			Arrays.sort(char_array);
			String tempstr = new String(char_array);			
			tempstr = tempstr.trim();
			if (str_map.containsKey(tempstr))
			{
                str_map.get(tempstr).add(str);
			}
			else
			{
                ArrayList<String> grp_str = new ArrayList<String>();
                grp_str.add(str);
				str_map.put(tempstr,grp_str);
			}
		}

		Iterator<ArrayList<String>> grp_itr = str_map.values().iterator();
		while(grp_itr.hasNext())
		{
			for(String str:grp_itr.next())
			{
				System.out.print(str+",");
			}
			System.out.println();
		}
	}
	public static void main(String args[] ) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> str_list = new ArrayList<String>();
		String str; 
		while((str= br.readLine())!=null)
		{
			str_list.add(str);
		}
		group_all_anagrams(str_list);
	}

}
