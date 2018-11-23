//GIft Stacking
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Gift[] gifts = new Gift[n];
      
      int maxx = 0;
      int maxy = 0;
      
      int[][] dp = new int[5001][5001];
      
      for(int k = 0; k < n; k++){  
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         dp[Math.max(a,b)][Math.min(a,b)] = Math.max(dp[Math.max(a,b)][Math.min(a,b)],c);
         dp[Math.max(a,c)][Math.min(a,c)] = Math.max(dp[Math.max(a,c)][Math.min(a,c)],b);
         dp[Math.max(c,b)][Math.min(c,b)] = Math.max(dp[Math.max(c,b)][Math.min(b,c)],a);         
         
         gifts[k] = new Gift(a,b,c);
         
      }
      
      int max = 0;
      
      for(int k = 5000; k >= 0; k--){
         for(int j = 5000; j >= 0; j--){
            if(dp[k][j] == 0) continue;
            for(int g = 0; g < n; g++){
               //loop through all orientations
               int a,b;
               a = Math.max(gifts[g].x,gifts[g].y);
               b = Math.min(gifts[g].x,gifts[g].y);
               if(a < k && b < j){
                  dp[a][b] = Math.max(dp[a][b],dp[k][j] + gifts[g].z);
                  max = Math.max(dp[a][b],max);
               }
               a = Math.max(gifts[g].x,gifts[g].z);
               b = Math.min(gifts[g].x,gifts[g].z);
               if(a < k && b < j){
                  dp[a][b] = Math.max(dp[a][b],dp[k][j] + gifts[g].y);
                  max = Math.max(dp[a][b],max);
               }
               a = Math.max(gifts[g].z,gifts[g].y);
               b = Math.min(gifts[g].z,gifts[g].y);
               if(a < k && b < j){
                  dp[a][b] = Math.max(dp[a][b],dp[k][j] + gifts[g].x);
                  max = Math.max(dp[a][b],max);
               }
            }
         }
      }
      
      out.println(max);
               
               
      
      out.close();
   }
   
   public static class Gift{
      int x;
      int y;
      int z;
      public Gift(int a, int b, int c){
         x=a;
         y=b;
         z=c;
      }
   }
   
}