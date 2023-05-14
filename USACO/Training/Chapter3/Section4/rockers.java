/*
TASK: rockers
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class rockers{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      //first n cd's, m disks used, fill last disk to t
      int[][][] dp = new int[n][m][t+1];
      for(int k = 0; k < n; k++) for(int j = 0; j < m; j++) Arrays.fill(dp[k][j],-1);
      
      //don't use
      dp[0][0][0] = 0;
      if(array[0] <= t){
         dp[0][0][array[0]] = 1;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < m; j++){
            for(int h = 0; h <= t; h++){
               if(dp[k][j][h] == -1) continue;
               
               //don't use
               dp[k+1][j][h] = Math.max(dp[k+1][j][h],dp[k][j][h]);
               
               //use, overflow
               if(h + array[k+1] > t){
                  if(j < m-1 && array[k+1] <= t){
                     dp[k+1][j+1][array[k+1]] = Math.max(dp[k+1][j+1][array[k+1]],dp[k][j][h]+1);
                  }
               } else {
               //use in same disk
                  dp[k+1][j][h+array[k+1]] = Math.max(dp[k+1][j][h+array[k+1]],dp[k][j][h]+1);
               }
            }
         }
      }
      
      int answer = 0;
      for(int k = 0; k < m; k++){
         for(int j = 0; j <= t; j++){
            answer = Math.max(answer,dp[n-1][k][j]);  
         }
      }
      
      out.println(answer);
        
      out.close();
   }
      
}