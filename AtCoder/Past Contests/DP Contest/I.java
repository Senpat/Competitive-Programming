//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      double[] p = new double[n];
      for(int k = 0; k < n; k++){
         p[k] = Double.parseDouble(st.nextToken());
      }
      
      double[][] dp = new double[n+1][n+1];     //dp[x][y] = probability that you get x heads and y tails in first x+y coins
      for(int k = 0; k <= n; k++) Arrays.fill(dp[k],0.0);
      dp[1][0] = p[0];
      dp[0][1] = 1.0-p[0];
      for(int k = 0; k <= n; k++){
         for(int j = 0; j <= n-k; j++){
            if(k == 0 && j == 0) continue;
            if(dp[k][j] != 0.0) continue;
            
            //got heads
            if(k-1 >= 0) dp[k][j] += dp[k-1][j] * p[k+j-1];
            //got tails
            if(j-1 >= 0) dp[k][j] += dp[k][j-1] * (1.0-p[k+j-1]);  
         }
      }
      
      double answer = 0.0;
      for(int k = n; k >= 0 && k > (n-k); k--){
         answer += dp[k][n-k];
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}