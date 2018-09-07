/*
TASK: nocross
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class nocross{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("nocross.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] a = new int[n+1];
      int[] b = new int[n+1];
      
      for(int k = 1; k <= n; k++) a[k] = Integer.parseInt(f.readLine());
      for(int k = 1; k <= n; k++) b[k] = Integer.parseInt(f.readLine());
      
      long[][] dp = new long[n+1][n+1];
      
      long max = 0L;
      
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            dp[k][j] = dp[k-1][j-1];
            //dp[k][j] = Math.max(dp[k][j],dp[k][j-1]);
            if(Math.abs(a[k]-b[j]) <= 4){
               dp[k][j]++;
               max = Math.max(dp[k][j],max);
            }
            long temp = Math.max(dp[k-1][j],dp[k][j-1]);
            dp[k][j] = Math.max(dp[k][j],temp);
         }
      }
      
      System.out.println(max);
      out.println(max);
      
      
      
      out.close();
   }
}