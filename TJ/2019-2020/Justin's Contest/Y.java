//make sure to make new file!
import java.io.*;
import java.util.*;

public class Y{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      String t = f.readLine();
      
      int n = s.length();
      int m = t.length();
      
      long[][] dp = new long[n+1][m+1];
      
      dp[0][0] = 1L;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j <= m; j++){
            if(dp[k][j] == 0) continue;
            
            dp[k+1][j] = (dp[k+1][j] + dp[k][j] + MOD)%MOD;
            if(j < m && s.charAt(k) == t.charAt(j)) dp[k+1][j+1] = (dp[k+1][j+1] + dp[k][j] + MOD)%MOD;
         }
      }
      
      out.println(dp[n][m]);
      

      
      
      
      
      
      out.close();
   }
   
      
}