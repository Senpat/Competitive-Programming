//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      long[][] dp = new long[n][m];
      dp[0][0] = 1L;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(dp[k][j] == 0) continue;
            if(k+1 < n && board[k+1][j] != '#') dp[k+1][j] = (dp[k+1][j] + dp[k][j] + MOD)%MOD;
            if(j+1 < m && board[k][j+1] != '#') dp[k][j+1] = (dp[k][j+1] + dp[k][j] + MOD)%MOD;
         }
      }
      
      out.println(dp[n-1][m-1]);
      
      
      
      out.close();
   }
   
      
}