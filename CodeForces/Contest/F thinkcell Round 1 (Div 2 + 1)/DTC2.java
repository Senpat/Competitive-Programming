//make sure to make new file!
import java.io.*;
import java.util.*;
//solves second subtask hopefully
public class DTC2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         String s = f.readLine();
         
         int[] array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Character.getNumericValue(s.charAt(k-1));
         }
         
         
         //dp[x] = min # to do all intervals ending in x
         long[] dp = new long[n+1];
         
         if(array[1] == 0) dp[1] = 0L;
         else dp[1] = 1L;
         
         if(2 <= n){
            if(array[2] == 1) dp[2]++;
            if(array[1] == 1 || array[2] == 1) dp[2]++;
         }
         
         if(3 <= n){
            if(array[3] == 1) dp[3] += 3L;
            else if(array[2] == 1) dp[3] += 2L;
            else if(array[1] == 1) dp[3] += 1L;
         }
         
         for(int k = 4; k <= n; k++){
            if(array[k] == 0){
               dp[k] = dp[k-1];
            } else {
               //place a 1 at k-1
               dp[k] = 3L + dp[k-3] + (long)(k-3);
            }
         }
         
         long sum = 0L;
         for(int k = 1; k <= n; k++){
            sum += dp[k];
         }
         out.println(sum);
         
      }
      
      
      
      
      out.close();
   }  
      
}