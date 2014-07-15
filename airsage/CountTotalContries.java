package airsage;

public class CountTotalContries 
{
	public void traverse(int[][] A,int[][] visitedA,int i,int j)
	{
		visitedA[i][j]=1;
		int currcolor=A[i][j];
		int rowsize=A.length;
		int colsize=A[0].length;
		if(i!=rowsize-1 && (A[i+1][j]==currcolor && visitedA[i+1][j]==0))
		{
			traverse(A, visitedA, i+1, j);
		}
        if(i!=0 &&  (A[i-1][j]==currcolor && visitedA[i-1][j]==0))
		{
			traverse(A, visitedA, i-1, j);
		}
        if(j!=colsize-1 && (A[i][j+1]==currcolor && visitedA[i][j+1]==0))
		{
			traverse(A, visitedA, i, j+1);
		}
        if(j!=0 && (A[i][j-1]==currcolor && visitedA[i][j-1]==0))
		{
			traverse(A, visitedA, i, j-1);
		}
	}
	public int solution(int[][] A)
	{
		int m=A.length;
		int n=A[0].length;
		int visitedA[][]=new int[m][n];
		int countrycount=0;
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(visitedA[i][j]==0)
				{
					countrycount++;
					traverse(A, visitedA, i, j);
				}
			}
		}
		return countrycount;
	}
	public static void main(String[] args)
	{
		int[][] array={{5,4,4},{4,3,4},{3,2,4},{2,2,2},{3,3,4},{1,4,4},{4,1,1}};
		CountTotalContries ct= new CountTotalContries();
		System.out.println(ct.solution(array));
	}
}
