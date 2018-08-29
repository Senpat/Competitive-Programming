/*
USER: pgz11901
TASK: money
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class money1{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("money.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
   
      StringTokenizer st = new StringTokenizer(f.readLine());
   
      int v = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
   
      long[] dp = new long[n+1];
      Arrays.fill(dp,0L);
      
      dp[0] = 1;
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < v; k++){
         int cur = Integer.parseInt(st.nextToken());
         for(int j = cur; j<=n; j++) dp[j] += dp[j-cur];
      }
      System.out.println(dp[n]); 
   }
}
