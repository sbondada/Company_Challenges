package airsage;

public class LinkedlistLength 
{
	public int solution(int A[],int N)
	{
		int listcounter=1;
		int inc=0;
		while(A[inc]!=-1)
		{
			inc=A[inc];
			listcounter++;
		}
		return listcounter;
	}
	public static void main(String[] args)
	{
		int[] array={1,4,-1,3,2};
		LinkedlistLength ll=new LinkedlistLength();
		System.out.println(ll.solution(array, array.length));

	}
}
