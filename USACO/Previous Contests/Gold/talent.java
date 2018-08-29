/*
TASK: talent
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class talent{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("talent.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] matrix = new int[n][2];
      
      int tt = 0;                                        //total talent
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < 2; j++){
            matrix[k][j] = Integer.parseInt(st.nextToken());
         }
         tt+=matrix[k][1];
      }
      
      long[][] dp = new long[n+1][tt+1];
            
      for(int k = 0; k <= n; k++) Arrays.fill(dp[k],Long.MAX_VALUE);
      
      dp[0][0] = 0L;
      
      double max = 0.0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j <= tt; j++){
            if(dp[k][j]<Long.MAX_VALUE){
            //don't use this cow
               dp[k+1][j] = Math.min(dp[k][j],dp[k+1][j]);
            
            //use this cow
               dp[k+1][j+matrix[k][1]] = Math.min(dp[k+1][j+matrix[k][1]],dp[k][j] + matrix[k][0]);
               if(dp[k+1][j+matrix[k][1]] >= m) max = Math.max(max,(double)(j+matrix[k][1])/(double)(dp[k+1][j+matrix[k][1]]));
            }
         }
      }
      
      System.out.print((long)Math.floor(max*1000));
      out.println((long)Math.floor(max*1000));
            
      out.close();
   }
}