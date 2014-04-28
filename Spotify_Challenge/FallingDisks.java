package Spotify_Challenge;

public class FallingDisks {

	public int falling_disks(int[] A,int[] B){
		if(B.length==0 | A.length==0){
			return -1;
		}
		int min= A[0];
		for(int index=1;index<A.length;index++){
			if(min<A[index]){
				A[index]=min;
			}
			else{
				min=A[index];
			}
		}
		int cntr=0;
		int bIndex=0;
		for(int index=A.length-1;index>=0;index--){
			if(A[index]>=B[bIndex]){
				cntr++;
				
				bIndex++;
				if(bIndex>B.length)
					return cntr;
			}
			
		}
		
		return cntr;
	}

	public static void main(String args[]) throws Exception{
		int A[]={5,6,4,3,6,2,3};
		int B[]={2,3,5,2,4};
		FallingDisks f= new FallingDisks();
		System.out.println(f.falling_disks(A, B));

	}

}
