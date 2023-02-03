/*
TASK: inflate
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class inflate{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] points = new int[m];
      int[] minutes = new int[m];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         points[k] = Integer.parseInt(st.nextToken());
         minutes[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] dp = new int[n+1];
      for(int k = 1; k <= n; k++){
         for(int j = 0; j < m; j++){
            if(k-minutes[j] >= 0){
               dp[k] = Math.max(dp[k],dp[k-minutes[j]] + points[j]);
            }
         }
      }
      
      int answer = 0;
      for(int k = 0; k <= n; k++){
         answer = Math.max(answer,dp[k]);
      }
      
      out.println(answer);
        
        
      out.close();
   }
      
}