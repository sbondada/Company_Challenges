package airsage;

public class StringCompare 
{
	public int solution(String S1,String S2)
	{
		int inc=0;
		while(inc<S1.length() && inc<S2.length())
		{
			if(S1.toLowerCase().charAt(inc)>S2.toLowerCase().charAt(inc))
			{
				return 1;
			}
			if(S1.toLowerCase().charAt(inc)<S2.toLowerCase().charAt(inc))
			{
				return -1;
			}
			inc++;
		}
		if(S1.length()==S2.length())
		{
			return 0;
		}
		else if(inc==S1.length())
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	public static void main(String[] args)
	{
		String a="abc";
		String b="aBC";
		StringCompare sa= new StringCompare();
		System.out.println(sa.solution(a,b));
	}
}
