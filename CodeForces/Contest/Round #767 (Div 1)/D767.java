//make sure to make new file!
import java.io.*;
import java.util.*;
//solves easy version
public class D767{

   public static long MOD = 1000000007L;
   public static long i2 = 500000004L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         long[][] dp = new long[n+1][n+1];
         
         for(int k = 0; k <= n; k++){
            dp[k][k] = (x*k + MOD)%MOD;
            dp[k][0] = 0L;
         }
         
         for(int k = 2; k <= n; k++){
            for(int j = 1; j < k; j++){
               long sum = (dp[k-1][j-1] + dp[k-1][j] + MOD)%MOD;
               dp[k][j] = (sum*i2 + MOD)%MOD;
            }
         }
         
         out.println(dp[n][m]);
      }
      
      
      
      
      out.close();
   }
   
      
}