package booking;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class FindDigits {
	/*
	 * Complete the function below.
	 */

	    static void findingDigits(int[] foo) 
	    {

	    	for(int num :foo)
	    	{
	    		int count=0;
	    		int temp = num;
	    		while(num!=0)
	    		{
	    			int tempnum = num%10;
	    			num=num/10;
	    			if (tempnum!=0 && temp%tempnum==0)
	    			{
	    				count++;
	    			}
	    		}
	    		System.out.println(count);
	    	}
	    	
	    }

        public static void main(String[] args)
        {
            Scanner in = new Scanner(System.in);
            
            int _foo_size = 0;
            _foo_size = Integer.parseInt(in.nextLine());
            int[] _foo = new int[_foo_size];
            int _foo_item;
            for(int _foo_i = 0; _foo_i < _foo_size; _foo_i++) {
                _foo_item = Integer.parseInt(in.nextLine());
                _foo[_foo_i] = _foo_item;
            }
            
            findingDigits(_foo);
            
        }
}