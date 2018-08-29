//Coloring Trees
//tutorial
import java.io.*;
import java.util.*;

public class C711b{

   public static final long INF = (long)1e18;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      
      for(int k = 1; k <= n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      long[][] cost = new long[n+1][c+1];
      
      for(int k = 1; k <= n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 1; j <= c; j++){
            cost[k][j] = Long.parseLong(st.nextToken());
         }
      }
      
      long[][][] dp = new long[n+1][b+1][c+1];
      
      for(int k = 0; k <= n; k++){
         for(int j = 0; j < b+1; j++){
            Arrays.fill(dp[k][j],INF);
         }
      }
      
      if(array[1] == 0){
         for(int k = 1; k <= c; k++){
            dp[1][1][k] = cost[1][k];
         }
      } else {
         dp[1][1][array[1]] = 0L;
      }
      
      for(int k = 2; k <= n; k++){
         for(int j = 1; j <= b; j++){
            if(array[k] == 0){
               for(int h = 1; h <= c; h++){
                  dp[k][j][h] = Math.min(dp[k][j][h],dp[k-1][j][h]+cost[k][h]);
                  for(int i = 1; i <= c; i++){
                     if(i!=h) dp[k][j][h] = Math.min(dp[k][j][h],dp[k-1][j-1][i]+cost[k][h]);
                  }
               }
            } else {
               dp[k][j][array[k]] = Math.min(dp[k][j][array[k]],dp[k-1][j][array[k]]);
               for(int i = 1; i <= c; i++){
                  if(array[k]!=i) dp[k][j][i] = Math.min(dp[k][j][i],dp[k][j-1][i]);
               }
            }
               
               
            
         }
      }
      
      long min = INF;
      for(int k = 0; k < c+1; k++){
         min = Math.min(dp[n][b][k],min);
      }
      
      if(min<INF) out.println(min);
      else out.println(-1);
      
      out.close();
   }
   
   public static boolean inBounds(long[][][] big, int a, int b, int c){
      return a < big.length && b < big[0].length && c < big[0][0].length;
   }
   
}