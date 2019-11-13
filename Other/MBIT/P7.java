//make sure to make new file!
import java.io.*;
import java.util.*;

public class P7{

   static long INF = Long.MAX_VALUE/2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      double[] dp = new double[n+1];
      
      for(int i = 0; i < n+1; i++){
         dp[i] = INF;
      }
      
      dp[0] = 0;
      
      long[] prefs = new long[n+1];
      long[] prefssqr = new long[n+1];
      
      for(int i = 1; i <= n; i++){
         prefs[i] = prefs[i-1]+array[i];
         prefssqr[i] = prefssqr[i-1]+(array[i])*(array[i]);
      }
      
      for(int k = 1; k <= n; k++){
         long sum = 0L;
         long sumsqr = 0L;
         for(int j = m-1; j < n; j++){
            if(k+j > n) continue;
            sum = prefs[k+j]-prefs[k-1];
            sumsqr = prefssqr[k+j]-prefssqr[k-1];
            double mean = ((double)sum/(double)(j+1));
            dp[k+j] = Math.min(dp[k+j],dp[k-1] + ((double)sumsqr-(double)mean*mean*(j+1)));
         }
      }
      
      if(Math.abs(dp[n] - (long)dp[n] - 1) < 0.000001){
         out.println((long)dp[n]+1);
      }else{
         out.println((long)dp[n]);
      }
     
      
      

      
      
      
      
      out.close();
   }
   
      
}