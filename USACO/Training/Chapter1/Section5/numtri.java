/*
USER: pgz11901
TASK: numtri
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class numtri{

   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] in = new int[n][n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < 1+k; j++){
            in[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      int[][] dp = new int[n][n];
      dp[0][0] = in[0][0];
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j < 1+k; j++){
            if(j == 0){                                  //first
               dp[k][j] = dp[k-1][j] + in[k][j];
            } else if (j == k){                          //last
               dp[k][j] = dp[k-1][j-1] + in[k][j];
            } else {
               dp[k][j] = Math.max(dp[k-1][j],dp[k-1][j-1]) + in[k][j];
            }
         }
      }
      
      
      int max = 0;
      for(int k = 0; k < n; k++){
         max = Math.max(max,dp[n-1][k]);
      }
      
      System.out.println(max);
      out.println(max);
      out.close();
   }
   
}
      

