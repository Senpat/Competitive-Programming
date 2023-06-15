//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1638{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[][] board = new char[n][n];
      for(int k = 0; k < n; k++){
         board[k] = f.readLine().toCharArray();
      }
      
      if(board[0][0] == '*'){
         out.println(0);
         out.close();
         return;
      }
      
      long[][] dp = new long[n][n];
      dp[0][0] = 1L;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(k+1 < n && board[k+1][j] != '*'){
               dp[k+1][j] = (dp[k+1][j] + dp[k][j] + MOD)%MOD;
            }
            if(j+1 < n && board[k][j+1] != '*'){
               dp[k][j+1] = (dp[k][j+1] + dp[k][j] + MOD)%MOD;
            }
         }
      }
      
      out.println(dp[n-1][n-1]);
      
      
      
      
      
      
      out.close();
   }
   
      
}