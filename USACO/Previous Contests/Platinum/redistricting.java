/*
TASK: redistricting
LANG: JAVA
*/
//O(N^2) DP
import java.io.*;
import java.util.*;

class redistricting{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("redistricting.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("redistricting.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String s = f.readLine();
      
      char[] array = s.toCharArray();
      
      int[] dp = new int[n+1];
      
      Arrays.fill(dp,Integer.MAX_VALUE);
      
      dp[0] = 0;
      
      for(int k = 0; k <= n; k++){
         int counter = 0;
         for(int j = 1; j <= m && k + j <= n; j++){
            counter += array[k+j-1] == 'H' ? -1 : 1;
            if(counter >= 0){
               dp[k+j] = Math.min(dp[k+j],dp[k] + 1);
            } else {
               dp[k+j] = Math.min(dp[k+j],dp[k]);
            }
         }
      }
      
      System.out.println(dp[n]);
      out.println(dp[n]);
      
            
      
      
      
      
        
        
      out.close();
   }
      
}  