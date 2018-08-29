//Coloring Trees
import java.io.*;
import java.util.*;

public class C711{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      long[][] cost = new long[n][c];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < c; j++){
            cost[k][j] = Long.parseLong(st.nextToken());
         }
      }
      
      long[][][] dp = new long[n][b+1][c+1];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < b+1; j++){
            Arrays.fill(dp[k][j],Long.MAX_VALUE);
         }
      }
      
      if(array[0] == 0){
         for(int k = 1; k <= c; k++){
            dp[0][1][k] = cost[0][k-1];
         }
      } else {
         dp[0][1][array[0]] = 0L;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < b+1; j++){
            for(int h = 1; h < c+1; h++){
               if(dp[k][j][h] != Long.MAX_VALUE){
                  if(array[k+1] == 0){
                     for(int i = 1; i < c+1; i++){
                        if(h==i){
                           if(inBounds(dp,k+1,j,i)) dp[k+1][j][i] = Math.min(dp[k+1][j][i],dp[k][j][h]+cost[k+1][i-1]);
                        } else {
                           if(inBounds(dp,k+1,j+1,i)) dp[k+1][j+1][i] = Math.min(dp[k+1][j+1][i],dp[k][j][h]+cost[k+1][i-1]);
                        }
                     }
                  } else {
                     if(array[k+1]==h){
                        if(inBounds(dp,k+1,j,h)) dp[k+1][j][h] = Math.min(dp[k+1][j][h],dp[k][j][h]);
                     } else {
                        if(inBounds(dp,k+1,j+1,array[k+1])) dp[k+1][j+1][array[k+1]] = Math.min(dp[k+1][j+1][array[k]],dp[k][j][h]);
                     }
                  }
               }
            }
         }
      }
      
      long min = Long.MAX_VALUE;
      for(int k = 0; k < c+1; k++){
         min = Math.min(dp[n-1][b][k],min);
      }
      
      if(min!=Long.MAX_VALUE) out.println(min);
      else out.println(-1);
      
      out.close();
   }
   
   public static boolean inBounds(long[][][] big, int a, int b, int c){
      return a < big.length && b < big[0].length && c < big[0][0].length;
   }
   
}