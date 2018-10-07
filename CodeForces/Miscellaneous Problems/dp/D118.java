//Caesar's Legions
import java.io.*;
import java.util.*;

public class D118{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n1 = Integer.parseInt(st.nextToken());                  //0
      int n2 = Integer.parseInt(st.nextToken());                  //1
      int i1 = Integer.parseInt(st.nextToken());
      int i2 = Integer.parseInt(st.nextToken());
      
      final int MOD = 100000000;
      
      int[][][] dp = new int[n1+1][n2+1][2];
      
      dp[0][0][0] = 1;
      dp[0][0][1] = 1;
      
      for(int k = 0; k <= n1; k++){
         for(int j = 0; j <= n2; j++){
            for(int i = j+1; i <= Math.min(n2,j+i2); i++){
               dp[k][i][0] = (dp[k][i][0] + dp[k][j][1]) % MOD;
            }
            for(int i = k+1; i <= Math.min(n1,k+i1); i++){
               dp[i][j][1] = (dp[i][j][1] + dp[k][j][0]) % MOD;
            }
         }
      }
      
      out.println((dp[n1][n2][0]+dp[n1][n2][1]) % MOD);
      
      
      out.close();
   }
   
}