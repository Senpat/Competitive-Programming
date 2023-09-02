//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1097{
   
   public static long[][] dp;
   public static long[] array;
   public static long[] psums;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      array = new long[n];
      psums = new long[n+1];
      psums[0] = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         psums[k+1] = psums[k] + array[k];
      }
      
      dp = new long[n][n];
      for(int k = 0; k < n; k++){
         Arrays.fill(dp[k],Long.MAX_VALUE);
      }
      
      long answer = dothing(0,n-1);
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static long dothing(int l, int r){
      if(dp[l][r] != Long.MAX_VALUE) return dp[l][r];
      if(l == r){
         dp[l][r] = array[l];
         return dp[l][r];
      }
      
      long ans = psums[r+1]-psums[l]-Math.min(dothing(l+1,r),dothing(l,r-1));
      
      dp[l][r] = ans;
      return dp[l][r];
   }
      
   
      
}