//make sure to make new file!
import java.io.*;
import java.util.*;

public class T{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      
      long[][] dp = new long[n][n];    //dp[x][y] is # of ways to fill first x numbers, and last number is at yth position (y <= x)
      dp[0][0] = 1L;
      
      for(int k = 0; k < n-1; k++){
         long sum = 0L;
         if(array[k] == '<'){
            //last number can't be highest
            for(int j = k; j >= 0; j--){
               sum = (sum + dp[k][j] + MOD)%MOD;
               dp[k+1][j] = sum;
            }
         } else {
            //last number can't be lowest
            for(int j = 1; j <= k+1; j++){
               sum = (sum + dp[k][j-1] + MOD)%MOD;
               dp[k+1][j] = sum;
            }
         }
      }
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         answer = (answer + dp[n-1][k] + MOD)%MOD;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}