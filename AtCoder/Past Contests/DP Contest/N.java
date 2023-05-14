//make sure to make new file!
import java.io.*;
import java.util.*;

public class N{
   
   public static long[][] rangesum;
   
   public static long[][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      rangesum = new long[n][n];
      
      for(int k = 0; k < n; k++){
         long sum = 0;
         for(int j = k; j < n; j++){
            sum += array[j];
            rangesum[k][j] = sum;
         }
      }
      
      dp = new long[n][n];
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],-1);
      
      dothing(0,n-1);
      
      out.println(dp[0][n-1]);
      
      
      
      
      
      
      out.close();
   }
   
   public static void dothing(int l, int r){
      if(dp[l][r] != -1) return;
      if(l == r) {
         dp[l][r] = 0;
         return;
      }
      
      long min = Long.MAX_VALUE;
      
      for(int k = l; k < r; k++){
         dothing(l,k);
         dothing(k+1,r);
         
         min = Math.min(min,dp[l][k] + dp[k+1][r] + rangesum[l][r]);
      }
      
      dp[l][r] = min;
      
   }
      
}