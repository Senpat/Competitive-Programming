//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1093{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int max = n*(n+1)/4+5;
      
      long[][] dp = new long[n+1][2*max];
      //zero is max
      
      //add 1 to first set
      dp[1][max+1] = 1L;
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j < 2*max; j++){
            if(dp[k][j] == 0) continue;
            
            if(j+(k+1) < 2*max){
               dp[k+1][j+(k+1)] += dp[k][j];
               if(dp[k+1][j+(k+1)] >= MOD) dp[k+1][j+(k+1)] -= MOD;
            }
            if(j-(k+1) > 0){
               dp[k+1][j-(k+1)] += dp[k][j];
               if(dp[k+1][j-(k+1)] >= MOD) dp[k+1][j-(k+1)] -= MOD;
            }
         }
      }
      
      out.println(dp[n][max]);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}