//Minimum Path
//dp
import java.io.*;
import java.util.*;

public class D1072b{

   public static char[][] board,ans;
   public static int[][] dp;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      board = new char[n+1][n+1];
      dp = new int[n+1][n+1];                   //count a's
      ans = new char[n+1][n+1];
      
      for(int k = 0 ; k <= n; k++){
         dp[k][0] = Integer.MAX_VALUE;     
         dp[0][k] = Integer.MAX_VALUE;
      }
      
      dp[1][0] = 0;
      
      int max = 0;
      
      for(int k = 1; k <= n; k++){
         String s = f.readLine();
         for(int j = 1; j <=n; j++){
            board[k][j] = s.charAt(j-1);
            dp[k][j] = Math.min(dp[k-1][j],dp[k][j-1]);
            if(board[k][j] != 'a'){
               dp[k][j]++;
            }
            if(dp[k][j]<=m){
               max = Math.max(k+j,max);
            }
            ans[k][j] = 'z'+1;
         }
      }
      
      StringBuilder answer = new StringBuilder();
      for(int k = 1; k < max; k++){
         answer.append('a');
      }
      
      if(max==0){
         ans[1][1] = board[1][1];
         max=1;
      }
      
      for(int k = 1; k <= n; k++){
         int j = max-k;
         if(j >= 1 && j <= n && dp[k][j] <=m){
            if(k+1 <= n) ans[k+1][j] = board[k+1][j];
            if(j+1 <= n) ans[k][j+1] = board[k][j+1];
         }
      }
         
      for(int d = max+1; d <= 2*n; d++){
         char min = 256;
         for(int k = 1; k <= n; k++){
            int j = d-k;
            if(j >= 1 && j <= n){
               min = (char)Math.min(min,ans[k][j]);
            }
         }
         
         answer.append(min);
         
         for(int k = 1; k <= n; k++){
            int j = d-k;
            if(j >= 1 && j <= n && ans[k][j] == min){
               if(k+1<=n) ans[k+1][j] = board[k+1][j];
               if(j+1<=n) ans[k][j+1] = board[k][j+1];
            }
         }
      }
            
      
      out.println(answer.toString());
      
      out.close();
   }
   
}