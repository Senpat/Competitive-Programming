//make sure to make new file!
import java.io.*;
import java.util.*;

public class C140{

   public static long MOD = 998244353L;

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
   
      int n = Integer.parseInt(f.readLine());
      
      //1-indexed
      int[][] constraints = new int[n+1][n+1];
   
      boolean trivial = false;
      
      for(int k = 1; k <= n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = k; j <= n; j++){
            constraints[k][j] = Integer.parseInt(st.nextToken());
            
            if(j == k && constraints[k][j] == 2) trivial = true;
         }
      }
      
      if(trivial){
         out.println(0);
         out.close();
         return;
      }
      
      //dp[n][0][last 1]
      //dp[n][1][last 0]
      long[][][] dp = new long[n+1][2][n+1];
      
      dp[1][0][0] = 1L;
      dp[1][1][0] = 1L;
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j <= n; j++){
            //add 0 to 0 or 1 to 1
            for(int x = 0; x <= 1; x++){
               if(dp[k][x][j] == 0) 
                  continue;
               //check constraints
               boolean fail = false;
               for(int h = k+1; h >= 1; h--){
                  if(constraints[h][k+1] == 0) 
                     continue;
                  
                  if(constraints[h][k+1] == 1 && h <= j){
                     fail = true;
                     break;
                  }
                  
                  if(constraints[h][k+1] == 2 && h > j){
                     fail = true;
                     break;
                  }
               }
               
               if(!fail){
                  dp[k+1][x][j] = (dp[k+1][x][j] + dp[k][x][j] + MOD)%MOD;
               }
               
            }
            
            //add 0 to 1 or 1 to 0
            for(int x = 0; x <= 1; x++){
               if(dp[k][x][j] == 0) 
                  continue;
               
               boolean fail = false;
               if(constraints[k+1][k+1] == 2){
                  fail = true;
               }
               
               for(int h = k; h >= 1; h--){
                  if(constraints[h][k+1] == 1){
                     fail = true;
                     break;
                  }
               }
               
               if(!fail){
                  dp[k+1][1-x][k] = (dp[k+1][1-x][k] + dp[k][x][j] + MOD)%MOD;
               }
            }
         
            
         
         
         
         }
         
      }
      
      long answer = 0L;
      
      for(int k = 0; k <= 1; k++){
         for(int j = 0; j <= n; j++){
            answer = (answer + dp[n][k][j] + MOD)%MOD;  
         }
      }
      
      out.println(answer);
   
   
   
   
   
   
   
      out.close();
   }

   
}