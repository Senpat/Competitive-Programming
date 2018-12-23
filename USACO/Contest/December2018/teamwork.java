/*
TASK: teamwork
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class teamwork{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("teamwork.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(f.readLine());
      }
      
      long[] dp = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         long max = 0L;
         for(int j = 0; j < m; j++){
            if(k+j > n) continue;
            max = Math.max(max,array[k+j]);
            dp[k+j] = Math.max(dp[k+j],dp[k-1] + max*(j+1));
         }
      }
      
      System.out.println(dp[n]);
      out.println(dp[n]);
      
      
        
        
      out.close();
   }
      
}