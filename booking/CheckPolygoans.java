package booking;

import java.io.*;
import java.util.Scanner;
public class CheckPolygoans{

public static void find_polygon_counts(String[] strings_array)
{
	int square_count=0;
	int rectangle_count=0;
	int no_count=0;
	for(String str:strings_array)
	{
		String[] side_str = str.split(" ");
		if(side_str[0].equals(side_str[2]) && side_str[1].equals(side_str[3]) && side_str[0].charAt(0)!='-' && side_str[1].charAt(0)!='-')

		{
			if (side_str[0].equals(side_str[1]))
			{
				square_count++;
			}
			else
			{
                rectangle_count++;
			}
		}
		else
		{
			no_count++;
		}
	}
    System.out.println(Integer.toString(square_count)+" "+Integer.toString(rectangle_count)+" "+Integer.toString(no_count));
}
	
public static void main(String args[] ) throws Exception 
{
	Scanner in = new Scanner(System.in);
    
    int _foo_size = 0;
    _foo_size = Integer.parseInt(in.nextLine());
    String[] string_size = new String[_foo_size];
    for(int i=0;i<_foo_size;i++)
    {
    	string_size[i] = in.nextLine();
    }
    find_polygon_counts(string_size);
}
}
