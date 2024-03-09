//make sure to make new file!
import java.io.*;
import java.util.*;

public class F320{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      
      int[] x = new int[n+1];
      x[0] = 0;
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         x[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] p = new int[n];       //price of fuel
      int[] a = new int[n];       //amount of fuel
      
      for(int k = 1; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         p[k] = Integer.parseInt(st.nextToken());
         a[k] = Integer.parseInt(st.nextToken());
      }
      
      //left to right - dp[x][y][z] is min cost to get to on nth station,
      //outbound trip has y fuel, return trip needs at least z fuel
      int[][][] dp = new int[n+1][h+1][h+1];
      for(int k = 0; k <= n; k++){
         for(int j = 0; j <= h; j++){
            Arrays.fill(dp[k][j],Integer.MAX_VALUE);
         }
      }
      
      dp[0][h][0] = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j <= h; j++){
            for(int i = 0; i <= h; i++){
               if(dp[k][j][i] == Integer.MAX_VALUE) continue;
               
               int diff = x[k+1]-x[k];
               
               int nextj = j-diff;
               int nexti = i+diff;
               if(nextj < 0 || nexti > h) continue;
               
               //do nothing
               dp[k+1][nextj][nexti] = Math.min(dp[k+1][nextj][nexti],dp[k][j][i]);
               
               if(k < n-1){
                  //add to outbound
                  int newj = Math.min(h,j-diff+a[k+1]);
                  dp[k+1][newj][nexti] = Math.min(dp[k+1][newj][nexti],dp[k][j][i] + p[k+1]);
                  
                  //add to inbound
                  int newi = Math.max(0,i+diff-a[k+1]);
                  dp[k+1][nextj][newi] = Math.min(dp[k+1][nextj][newi],dp[k][j][i] + p[k+1]);
               }
               
            }
         }
      }
      
      int answer = Integer.MAX_VALUE;
      
      for(int k = 0; k <= h; k++){
         for(int j = 0; j <= k; j++){     //outbound fuel >= inbound fuel needed
            answer = Math.min(answer,dp[n][k][j]);
         }
      }
      
      if(answer == Integer.MAX_VALUE){
         out.println("-1");
      } else {
         out.println(answer);
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}