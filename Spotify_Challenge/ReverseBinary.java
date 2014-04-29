package Spotify_Challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReverseBinary {
	public static void main(String Args[])
	{
		try
		{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input=br.readLine();
			long inputLong=Long.parseLong(input);
			String inputBinary=Long.toBinaryString(inputLong);
			ReverseBinary rev = new ReverseBinary();
			String reversedInputBinary=rev.reverseBinary(inputBinary);
			long reversedLong=Long.parseLong(reversedInputBinary, 2);
			System.out.println(reversedLong);
		}
		catch (Exception e)
		{
			System.out.println("exception occured due to the entered values"+e);
		}
	}
	public String reverseBinary(String inputBinary)
	{
//		System.out.println("input string "+inputBinary.toString());
		int i=0,j=inputBinary.length()-1;
//		System.out.println(j);
		char[] inputBinaryArray=inputBinary.toCharArray();
		char temp=0;
		while(i<=j)
		{
			temp=inputBinaryArray[i];
			inputBinaryArray[i]=inputBinaryArray[j];
			inputBinaryArray[j]=temp;
			i++;
			j--;
		}
//		System.out.println("reversed string "+String.valueOf(inputBinaryArray));
		return String.valueOf(inputBinaryArray);
	}

}
