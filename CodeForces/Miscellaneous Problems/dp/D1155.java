//Beautiful Array
import java.io.*;
import java.util.*;

public class D1155{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[][] dp = new long[n][3];                                     //0 -> before mul, 1 -> during mul, 2 -> after mul
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MIN_VALUE);
      
      long max = 0L;
      for(int k = 0; k < n; k++){
         dp[k][0] = Math.max(dp[k][0],array[k]);
         dp[k][1] = Math.max(dp[k][1],array[k]*m);
         for(int j = 0; j < 3; j++){
            if(dp[k][j] == Long.MIN_VALUE || k == n-1) continue;
            if(j == 0){
               dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j] + array[k+1]);
               dp[k+1][j+1] = Math.max(dp[k+1][j+1],dp[k][j] + array[k+1]*m);
            } else if(j == 1){
               dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j] + array[k+1]*m);
               dp[k+1][j+1] = Math.max(dp[k+1][j+1],dp[k][j] + array[k+1]);
            } else {
               dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j] + array[k+1]);
            }
         }
         max = Math.max(Math.max(max,dp[k][0]),Math.max(dp[k][1],dp[k][2]));
      }
      
      out.println(max);
      

      
      
      
      
      out.close();
   }
   
      
}