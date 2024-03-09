//Sereja and Intervals
import java.io.*;
import java.util.*;

public class E367{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken())-1;
      
      if(n > m){
         out.println(0);
         out.close();
         return;
      }
      
      long[][][] dp = new long[2][n+1][n+1];       //int on segment, # of intervals opened, # of open intervals
      
      
      if(x != 0){
         dp[0][0][0] = 1L;    //don't do anything
      }
      dp[0][1][1] = 1L;       //open interval
      dp[0][1][0] = 1L;       //open and close interval
      
      for(int k = 1; k < m; k++){
         int to = k&1;
         int from = to^1;
         
         for(int j = 0; j <= n; j++){
            for(int h = 0; h <= j; h++){
               if(dp[from][j][h] == 0L) continue;
               
               if(x != k){
                  //don't do anything
                  dp[to][j][h] = (dp[to][j][h] + dp[from][j][h] + MOD)%MOD;
                  //close
                  if(h > 0){
                     dp[to][j][h-1] = (dp[to][j][h-1] + dp[from][j][h] + MOD)%MOD;
                  }
               }
               
               if(j < n){
                  //open
                  dp[to][j+1][h+1] = (dp[to][j+1][h+1] + dp[from][j][h] + MOD)%MOD;
                  //open and close
                  dp[to][j+1][h] = (dp[to][j+1][h] + dp[from][j][h] + MOD)%MOD;
               }
               
               dp[from][j][h] = 0L;
            }
         }
      }
      
      long answer = dp[(m-1)&1][n][0];
      for(long k = 2; k <= (long)n; k++){
         answer = (answer * k + MOD)%MOD;
      }
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}