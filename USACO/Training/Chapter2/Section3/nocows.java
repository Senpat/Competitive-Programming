/*
TASK: nocows
LANG: JAVA
*/
//hint
import java.io.*;
import java.util.*;

class nocows{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
      
      int MOD = 9901;
      
      int N = 200;
      int M = 100;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(n % 2 == 0){
         out.println(0);
         out.close();
         return;
      }
      
      int[][] dp = new int[N+1][M+1];
      dp[0][0] = 1;
      dp[1][1] = 1;
      for(int k = 3; k <= N; k += 2){
         for(int j = 2; j <= M; j++){
            
            for(int nl = 1; nl <= k-1; nl+=2){
               int nr = k-1-nl;
               
               int suml = 0;
               int sumr = 0;
               for(int h = 0; h <= j-2; h++){
                  suml = (suml + dp[nr][h] + MOD)%MOD;
                  sumr = (sumr + dp[nl][h] + MOD)%MOD;
               }
               //max on left, less than max on right
               dp[k][j] = (dp[k][j] + dp[nl][j-1] * suml + MOD)%MOD;
               
               //less than max on left, max on right
               
               dp[k][j] = (dp[k][j] + dp[nr][j-1] * sumr + MOD)%MOD;
               
               //both max
               dp[k][j] = (dp[k][j] + dp[nl][j-1] * dp[nr][j-1] + MOD)%MOD;
            }
         }
      }
      
      System.out.println(dp[n][m]);
      out.println(dp[n][m]);
      
        
        
      out.close();
   }
      
}