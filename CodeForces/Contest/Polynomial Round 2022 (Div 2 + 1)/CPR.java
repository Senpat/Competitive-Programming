//make sure to make new file!
import java.io.*;
import java.util.*;

public class CPR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         n--;
         
         char[] input = f.readLine().toCharArray();
         
         int[] array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Character.getNumericValue(input[k-1]);
         }
         
         int[] answer = new int[n+1];
         answer[1] = 1;
         
         int last = array[1];
         int streak = 1;
         
         for(int k = 2; k <= n; k++){
            if(array[k] == last){
               streak++;
            } else {
               last = array[k];
               streak = 1;
            }
            
            answer[k] = k+1-streak;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
            

      }
      
      
      
      
      out.close();
   }
   
      
}