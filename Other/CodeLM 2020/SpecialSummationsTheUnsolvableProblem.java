import java.util.Scanner;
import java.io.*;
import java.util.*;
public class SpecialSummationsTheUnsolvableProblem {
	static Scanner s = new Scanner(System.in);
	
   public static ArrayList<HashSet<String>> answer;
   
	public static void main(String[] args) {
        int n = s.nextInt();

		// code to solve the problem.  You can write and call other methods as well.
		
      
      answer=  new ArrayList<HashSet<String>>(16);
      for(int k = 0; k <= 15; k++) answer.add(new HashSet<String>());
      
      
      dothing(0,0,"");
      
      PriorityQueue<String> pq = new PriorityQueue<String>();
      for(String s : answer.get(n)){
         pq.add(s);
      }
      
      while(!pq.isEmpty()){
         System.out.println(pq.poll());
      }
      
      
      
      
	}
   
   
   public static void dothing(int i, int sum, String s){
      if(i > 15 || sum > 15) return;
      if(sum > 0 && s.length() >= 3){
         answer.get(sum).add(s);
      }
      
      String newstring = s;
      if(s.equals("")){
         newstring = "" + (i+1);
      } else {
         newstring += " + " + (i+1);
      }
      
      dothing(i+1,sum+i+1,newstring);
      dothing(i+1,sum,s);
   }
      
      
   
}
