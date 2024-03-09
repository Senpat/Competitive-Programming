//make sure to make new file!
import java.io.*;
import java.util.*;
//solves D1 hopefully
public class DTC1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         String s = f.readLine();
         
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
         }
         
         long answer = 0L;
         for(int l = 0; l < n; l++){
            for(int r = l; r < n; r++){
               
               //dp[x][y] -> min # of 1s such that all pref of 1s is satisfied and last element is y
               long[][] dp = new long[r-l+1][2];
               for(int k = 0; k < r-l+1; k++){
                  dp[k][0] = Long.MAX_VALUE/2L;
                  dp[k][1] = Long.MAX_VALUE/2L;
               }
               
               if(array[l] == 0){
                  dp[0][0] = 0L;
                  dp[0][1] = 1L;
               } else {
                  dp[0][1] = 1L;
                  if(r > l){
                     dp[1][1] = 1L;
                  }
               }
               
               for(int k = 1; k < r-l+1; k++){
                  if(array[k+l] == 1){
                     //add 0 (prev 1)
                     dp[k][0] = Math.min(dp[k][0],dp[k-1][1]);
                     //add 0 and do 1 next
                     if(k+1 < r-l+1){
                        dp[k+1][1] = Math.min(dp[k+1][1],Math.min(dp[k-1][0],dp[k-1][1]) + 1L);
                     }
                     //add 1 here
                     dp[k][1] = Math.min(dp[k][1],Math.min(dp[k-1][0],dp[k-1][1]) + 1L);
                  } else {
                     //add 0
                     dp[k][0] = Math.min(dp[k][0],Math.min(dp[k-1][0],dp[k-1][1]));
                     //add 1
                     dp[k][1] = Math.min(dp[k][1],Math.min(dp[k-1][0],dp[k-1][1]) + 1L);
                  }
               }
               
               
               answer += Math.min(dp[r-l+1 -1][0],dp[r-l+1 -1][1]);
            }
         }
            
            
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}