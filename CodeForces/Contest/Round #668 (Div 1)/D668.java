//make sure to make new file!
import java.io.*;
import java.util.*;

public class D668{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n==1){
         out.println("Second");
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int[] array = new int[2*n+1];
         for(int k = 1; k <= 2*n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         out.println("2");
      } else if(n%2 == 0){
         out.println("First");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + k);
         }
         for(int k = 1; k <= n; k++){
            sj.add("" + k);
         }
         out.println(sj.toString());
      } else {
         out.println("First");
         StringJoiner sj = new StringJoiner(" ");
         
         int[] answer = new int[2*n];
         for(int k = 0; k < n; k++){
            answer[2*k] = k+1;
            answer[2*k+1] = k+1;
         }
         
         //shuffle array
         Random rand = new Random();
		
   		for (int i = 0; i < answer.length; i++) {
   			int randomIndexToSwap = rand.nextInt(answer.length);
   			int temp = answer[randomIndexToSwap];
   			answer[randomIndexToSwap] = answer[i];
   			answer[i] = temp;
   		}
         
         for(int k = 0; k < 2*n; k++){
            sj.add("" + answer[k]);
         }
         
         out.println(sj.toString());
      }
      
      int i = Integer.parseInt(f.readLine());
      
      
      
      out.close();
   }
   
      
}