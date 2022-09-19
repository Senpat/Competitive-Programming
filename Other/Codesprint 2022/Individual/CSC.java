//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      double p = Double.parseDouble(st.nextToken());
      
      int N = 3005;
      double[] pexp = new double[N];
      pexp[0] = 1.0;
      for(int k = 1; k < N; k++){
         pexp[k] = pexp[k-1]*p;
      }
      
      double[][] dp = new double[n+1][m+1];       //prob to get the point where there are n pokemon remaining and crossed m steps
      
      dp[n][0] = 1.0;
      
      for(int j = 0; j < m; j++){
         for(int k = n; k >= 1; k--){
            if(k == 1){
               //test next pair
               dp[k][j+1] += dp[k][j]*0.5;
            } else {
            
            //probability that everyone refuses
               dp[k-1][j] += dp[k][j]*pexp[k];
            
            //probability that someone doesn't refuse and finds the bad step
               dp[k-1][j+1] += dp[k][j]*(1-pexp[k])*0.5;
            
            //good step
               dp[k][j+1] += dp[k][j]*(1-pexp[k])*0.5;          
            
            }
         }
      }
      
      double answer = 0.0;
      for(int k = 1; k <= n; k++) answer += dp[k][m];
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}