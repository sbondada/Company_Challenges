package Spotify_Challenge;

import java.util.Stack;

public class ExpressionEvaluation {
	public int stack_machine_emulator(String S){
		if(S== null || S.length()==0){
			return -1;
		}
		char inpArray[]= new char[S.length()];
		inpArray= S.toCharArray();
		Stack<Integer> st= new Stack<Integer>();
		for(int index=0;index<inpArray.length;index++){
			if(inpArray[index]=='+'){
				if(st.size()>=2){
					st.push(new Integer((st.pop().intValue() + st.pop().intValue())));
				}
				else
					return -1;
			}
			else if(inpArray[index]=='*'){
				if(st.size()>=2){
					st.push(new Integer((st.pop().intValue() * st.pop().intValue())));
				}
				else
					return -1;

			}
			else{
				st.push(new Integer(Character.getNumericValue(inpArray[index])));
			}


		}
		return st.pop().intValue(); 
	}


	public static void main(String args[]) throws Exception{

		ExpressionEvaluation e= new ExpressionEvaluation();
		System.out.println(e.stack_machine_emulator("1+1"));


	}

}
