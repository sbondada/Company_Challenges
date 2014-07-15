package airsage;

public class CountLatticePoints 
{
	private static int n=4;
	public static void main(String [] args){
		
		System.out.println(n);
		
		int y=n;
		int x=0;
		int count=0;
		
		for(int i=0;i<=n; i++){
			if(inside(x,y)){
				System.out.println("Point "+x+", "+y);
				count=count+y+1;
				x=x+1;
			}
			while(!inside(x,y)) y=y-1;
		}
		int result= 4*count- 4*n-3;
		System.out.println(result);
	}

	private static boolean inside(int x, int y) {
		// TODO Auto-generated method stub
		int sum=x*x+y*y;
		
		//double dist=Math.sqrt(sum);
		
		if((sum)<=(n*n)) return true;
		return false;
	}
}
