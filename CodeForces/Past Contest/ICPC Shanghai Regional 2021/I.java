//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] v = new long[n];
      int[] t = new int[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         v[k] = Long.parseLong(st.nextToken());
         t[k] = Integer.parseInt(st.nextToken());
      }
      
      int X = 2601;
      long[][][] dp = new long[n][m+1][X];
      for(int k = 0; k < n; k++) 
         for(int j = 0; j <= m; j++) 
            for(int h = 0; h < X; h++) dp[k][j][h] = Long.MIN_VALUE;
      
      dp[0][0][0] = 0L;
      dp[0][0][t[0]] = v[0];
      if(m >= 1) dp[0][1][2*t[0]] = v[0];
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= m; j++){
            for(int h = 0; h < X; h++){
               if(dp[k][j][h] == Long.MIN_VALUE) 
                  continue;
               
               //don't use
               dp[k+1][j][h] = Math.max(dp[k+1][j][h],dp[k][j][h]);
               
               
               //use on bigger set, don't double
               dp[k+1][j][h+t[k+1]] = Math.max(dp[k+1][j][h+t[k+1]],dp[k][j][h]+v[k+1]);
               
               //use on bigger set, double
               if(j < m)
                  dp[k+1][j+1][h+2*t[k+1]] = Math.max(dp[k+1][j+1][h+2*t[k+1]],dp[k][j][h]+v[k+1]);
               
               //use on smaller set, don't double
               dp[k+1][j][Math.abs(h-t[k+1])] = Math.max(dp[k+1][j][Math.abs(h-t[k+1])],dp[k][j][h]+v[k+1]);
               
               //use on smaller set, double
               if(j < m)
                  dp[k+1][j+1][Math.abs(h-2*t[k+1])] = Math.max(dp[k+1][j+1][Math.abs(h-2*t[k+1])],dp[k][j][h]+v[k+1]);
               
            }
         }
      }
      
      long answer = Long.MIN_VALUE;
      
      for(int k = 0; k <= m; k++){
         answer = Math.max(answer,dp[n-1][k][0]);
      }
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}