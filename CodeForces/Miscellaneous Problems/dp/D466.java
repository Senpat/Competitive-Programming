//Increase Sequence
import java.io.*;
import java.util.*;

public class D466{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      boolean fail = false;
      for(int k = 0; k < n; k++){
         array[k] = m-Integer.parseInt(st.nextToken());
         if(array[k] < 0){
            fail = true;
         }
      }
      
      if(fail){
         out.println(0);
         out.close();
         return;
      }
      
      //open and close interval trick
      long[][] dp = new long[n][m+1];         //# of elements, # of open intervals
      
      if(array[0] == 0){
         dp[0][0] = 1L;
      }
      if(array[0] == 1){
         dp[0][1] = 1L;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= m; j++){
            if(dp[k][j] == 0L) continue;
            
            if(array[k+1] == j-1){
               //close one
               dp[k+1][j-1] = (dp[k+1][j-1] + (long)j * dp[k][j] + MOD)%MOD;
            }
            
            if(array[k+1] == j){
               //maintain same # of intervals
               dp[k+1][j] = (dp[k+1][j] + dp[k][j] + MOD)%MOD;
               
               //close one and start a new one
               dp[k+1][j] = (dp[k+1][j] + (long)j * dp[k][j] + MOD)%MOD;
            }
            
            if(array[k+1] == j+1){
               //open one
               dp[k+1][j+1] = (dp[k+1][j+1] + dp[k][j] + MOD)%MOD;
            }
            
         }
      }
      
      //at the end, either have 0 intervals remaining or 1 interval remaining (which you close)
      long answer = (dp[n-1][0] + dp[n-1][1] + MOD)%MOD;
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}