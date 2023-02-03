//make sure to make new file!
import java.io.*;
import java.util.*;

public class CTDB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long s = Long.parseLong(st.nextToken());
         
         long[] array = new long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long[][] extremes = new long[n][2];
         for(int k = 1; k < n-1; k++){
            if(s == 0 || s >= array[k]){
               extremes[k][0] = 0L;
               extremes[k][1] = array[k];
            } else {
               extremes[k][0] = s;
               extremes[k][1] = array[k]-s;
            }
         }
         
         //dp[k][j] -> minimum to do the first k, for the kth one you used j
         long[][] dp = new long[n][2];
         dp[1][0] = array[0] * extremes[1][0];
         dp[1][1] = array[0] * extremes[1][1];
         for(int k = 2; k < n-1; k++){
            dp[k][0] = Math.min(dp[k-1][0] + extremes[k-1][1] * extremes[k][0], dp[k-1][1] + extremes[k-1][0] * extremes[k][0]);
            dp[k][1] = Math.min(dp[k-1][0] + extremes[k-1][1] * extremes[k][1], dp[k-1][1] + extremes[k-1][0] * extremes[k][1]);
         }
         
         long answer = Math.min(dp[n-2][0] + extremes[n-2][1] * array[n-1], dp[n-2][1] + extremes[n-2][0] * array[n-1]);
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}