//make sure to make new file!
import java.io.*;
import java.util.*;
//TLE, same solution passed in 1996/2000ms in c++ O(N^3)
public class E1027{
   
   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(m == 1){
         out.println("0");
         out.close();
         return;
      }
      
      
      long[] array = new long[n+1];            //number of ways such that max chunk is n
      array[0] = 0;
      array[1] = 1;
      
      for(int i = 2; i <= n; i++){
         long[][][] dp = new long[n][i][2];
         
         dp[0][0][0] = 1;
         
         for(int k = 0; k < n-1; k++){
            for(int j = 0; j < i; j++){
               for(int h = 0; h < 2; h++){
                  //simulate adding same color
                  if(j<i-1){
                     if(j == i-2){
                        dp[k+1][j+1][1] = (dp[k+1][j+1][1]+dp[k][j][h]+MOD)%MOD;
                     } else {
                        dp[k+1][j+1][h] = (dp[k+1][j+1][h]+dp[k][j][h]+MOD)%MOD;
                     }
                  }
                  //simulate adding different color
                  dp[k+1][0][h] = (dp[k+1][0][h]+dp[k][j][h]+MOD)%MOD;
               }
            }
         }
         
         for(int k = 0; k < i; k++){
            array[i] = (array[i] + dp[n-1][k][1]+MOD)%MOD;
         }
      }
      
      //psums
      long[] psums = new long[n+2];
      
      psums[0] = 0L;
      
      for(int k = 1; k <= n+1; k++){
         psums[k] = (psums[k-1]+array[k-1]+MOD)%MOD;
      }
      
      long answer = 0L;
      
      for(int k = 1; k <= n; k++){
         if(k >= (int)m) continue;
         int i = m/k;
         if(k*i==m) i--;
         i = Math.min(i,n);
         long prod = (array[k]*psums[i+1]+MOD)%MOD;
         answer = (answer + prod + MOD)%MOD;
      }
      
      answer = (answer*2 + MOD)%MOD;
      out.println(answer);
      
      
      
      
      
      out.close();
   }
}