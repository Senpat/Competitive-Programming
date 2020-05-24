//make sure to make new file!
import java.io.*;
import java.util.*;

public class E627{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int l = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][] dp = new int[n+1][h];
      for(int k = 0; k <= n; k++){
         Arrays.fill(dp[k],-1);
      }
      
      dp[0][0] = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < h; j++){
            if(dp[k][j] == -1) continue;
            
            if((j+array[k]-1)%h >= l && (j+array[k]-1)%h <= r){
               dp[k+1][(j+array[k]-1)%h] = Math.max(dp[k+1][(j+array[k]-1)%h],dp[k][j]+1);
            } else {
               dp[k+1][(j+array[k]-1)%h] = Math.max(dp[k+1][(j+array[k]-1)%h],dp[k][j]);
            }
            if((j+array[k])%h >= l && (j+array[k])%h <= r){
               dp[k+1][(j+array[k])%h] = Math.max(dp[k+1][(j+array[k])%h],dp[k][j]+1);
            } else {
               dp[k+1][(j+array[k])%h] = Math.max(dp[k+1][(j+array[k])%h],dp[k][j]);
            }
            
         }
      }
      
      int answer = 0;
      for(int k = 0; k < h; k++){
         answer = Math.max(answer,dp[n][k]);
      }
      out.println(answer);
      
      
      
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}