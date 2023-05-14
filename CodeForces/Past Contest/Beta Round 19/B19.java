//make sure to make new file!
import java.io.*;
import java.util.*;

public class B19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] t = new int[n];
      long[] c = new long[n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         t[k] = a;
         c[k] = b;
      }
      
      long[][] dp = new long[n][2*n+1];      //dp[x][y] is minimum cost for first x items, y+n time (y can be negative)
      
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MAX_VALUE);
      
      //initial values
      dp[0][n+t[0]] = c[0];
      dp[0][n-1] = 0;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= 2*n; j++){
            if(dp[k][j] == Long.MAX_VALUE) continue;
            //use (k+1)th item
            int newj = Math.min(2*n,j+t[k+1]);
            dp[k+1][newj] = Math.min(dp[k+1][newj],dp[k][j] + c[k+1]);
            
            //don't use
            dp[k+1][j-1] = Math.min(dp[k+1][j-1],dp[k][j]);
         }
      }
      
      long answer = Long.MAX_VALUE;
      for(int k = n; k < 2*n+1; k++){
         answer = Math.min(answer,dp[n-1][k]);
      }
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}