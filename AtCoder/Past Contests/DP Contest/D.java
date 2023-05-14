//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      
      int[] weights = new int[n];
      long[] v = new long[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int wi = Integer.parseInt(st.nextToken());
         long vi = Long.parseLong(st.nextToken());
         
         weights[k] = wi;
         v[k] = vi;
      }
      
      long[][] dp = new long[n][w+1];        //dp[x][y] stores the max value using first x items and y weight
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],-1L);
      
      dp[0][0] = 0L;
      dp[0][weights[0]] = v[0];
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= w; j++){
            if(dp[k][j] == -1) continue;
            //don't use
            dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j]);
            
            //use
            if(j+weights[k+1] <= w){
               dp[k+1][j+weights[k+1]] = Math.max(dp[k+1][j+weights[k+1]],dp[k][j] + v[k+1]);
            }
         }
      }
      
      long answer = 0;
      for(int k = 0; k <= w; k++){
         answer = Math.max(answer,dp[n-1][k]);
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}