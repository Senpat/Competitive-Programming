//Python Indentation
import java.io.*;
import java.util.*;
//tle
public class C909{

   public static long MOD = 1000000007;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = new char[n];
      
      for(int k = 0; k < n; k++){
         array[k] = f.readLine().charAt(0);
      }
      
      long[][] dp = new long[n][n];
      
      dp[0][0] = 1;
      
      long[] psums = new long[n+1];
      
      for(int k = 0; k < n-1; k++){
      
         long[] cur = new long[n];
         for(int j = 0; j < n-1; j++){
            if(dp[k][j] == 0) continue;
            if(array[k] == 'f'){
               dp[k+1][j+1]+=dp[k][j];
               if(dp[k+1][j+1] > MOD) dp[k+1][j+1] -= MOD;
               
            } else {
               /*for(int h = 0; h <= j; h++){
                  dp[k+1][h]+=dp[k][j];
                  if(dp[k+1][h] > MOD) dp[k+1][h] -= MOD;
               }*/
               cur[j] += dp[k][j];
               if(cur[j] > MOD) cur[j] -= MOD;
            }
         }
         
         for(int j = 1; j <= n; j++){
            psums[j] = psums[j-1] + cur[j-1];
            if(psums[j] > MOD) psums[j] -= MOD;
         }
         
         for(int j = 0; j < n; j++){
            dp[k+1][j] += psums[n] - psums[j];
            if(dp[k+1][j] > MOD) dp[k+1][j] -= MOD;
            while(dp[k+1][j] < MOD) dp[k+1][j] += MOD;
         }
         
         
      }
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         answer = (answer + dp[n-1][k] + MOD) % MOD;
      }
      
      out.println(answer);
      
      out.close();
   }
   
}