//Easy Problem
import java.io.*;
import java.util.*;
//upsolve using dp (semi-t)
public class D57b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[][] dp = new long[n][4];
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MAX_VALUE);
      
      if(s.charAt(0) == 'h'){
         dp[0][0] = array[0];
         dp[0][1] = 0L;
      } else {
         dp[0][0] = 0L;
      }
      
      
      String hard = "hard";
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < 4; j++){
            if(dp[k][j] == Long.MAX_VALUE) continue;
            if(s.charAt(k+1) == hard.charAt(j)){
               dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j] + array[k+1]);
               if(j < 3) dp[k+1][j+1] = Math.min(dp[k+1][j+1],dp[k][j]);
            } else {
               dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j]);
            }
         }
      }
      
      long answer = Long.MAX_VALUE;
      
      for(int k = 0; k < 4; k++){
         answer = Math.min(dp[n-1][k],answer);
      }
      
      out.println(answer);
            
            
            
      
      
      
      
      
      out.close();
   }
   
}