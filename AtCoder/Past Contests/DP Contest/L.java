//make sure to make new file!
import java.io.*;
import java.util.*;

public class L{
   
   public static long[] array;
   public static long[][] sum;
   public static long[][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      sum = new long[n][n];         //sum[l][r] is sum from l to r
      for(int k = 0; k < n; k++){
         long cursum = 0L;
         for(int j = k; j < n; j++){
            cursum += array[j];
            sum[k][j] = cursum;
         }
      }
      
      dp = new long[n][n];          //dp[l][r] is max sum for player that plays first
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MAX_VALUE);
      dothing(0,n-1);
      
      long x = dp[0][n-1];
      long y = sum[0][n-1] - x;
      long answer = x-y;
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dothing(int l, int r){
      if(dp[l][r] != Long.MAX_VALUE) return;
      if(l == r) {
         dp[l][r] = array[l];
         return;
      }
      
      dothing(l+1,r);
      dothing(l,r-1);
      
      dp[l][r] = Math.max(array[l] + sum[l+1][r]-dp[l+1][r], array[r] + sum[l][r-1]-dp[l][r-1]);
      
   }
   
      
}