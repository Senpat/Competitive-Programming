//make sure to make new file!
import java.io.*;
import java.util.*;

public class E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());
      
      int[] w = new int[n];
      int[] v = new int[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         w[k] = Integer.parseInt(st.nextToken());
         v[k] = Integer.parseInt(st.nextToken());
      }
      
      int M = 100005;
      
      int[][] dp = new int[n][M];         //dp[x][y] stores minimum weight of knapsack for first x items and value of y
      
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],W+1);
      
      dp[0][0] = 0;
      dp[0][v[0]] = w[0];
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < M; j++){
            if(dp[k][j] == W+1) continue;
            //don't add
            dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j]);
            //add
            dp[k+1][j+v[k+1]] = Math.min(dp[k+1][j+v[k+1]],dp[k][j] + w[k+1]);
         }
      }
      
      int answer = 0;
      for(int k = 0; k < M; k++){
         if(dp[n-1][k] <= W) answer = k;
      }
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}