//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class P1727{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      double md = (double)m;
      
      double[][] dp = new double[n][m+1];    //probability that after n dudes, m is the max
      
      for(int k = 1; k <= m; k++){
         dp[0][k] = 1.0/md;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 1; j <= m; j++){
            dp[k+1][j] += dp[k][j] * (double)j / md;
            for(int h = j+1; h <= m; h++){
               dp[k+1][h] += dp[k][j] / md;
            }
         }
      }
      
      double answer = 0.0;
      for(int k = 1; k <= m; k++){
         answer += (double)k * dp[n-1][k];
      }
      
      out.println(new BigDecimal(answer).setScale(6,RoundingMode.HALF_EVEN));
      
      
      
      
      
      
      
      out.close();
   }
   
      
}