//make sure to make new file!
import java.io.*;
import java.util.*;

public class S{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] cin = f.readLine().toCharArray();
      int n = cin.length;
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Character.getNumericValue(cin[k]);
      }
      
      int d = Integer.parseInt(f.readLine());
      
      long[][][] dp = new long[n][d][2];           //first n digits, mod d, 0 -> < max number, 1 -> = max number
      
      //initial values
      //max
      dp[0][array[0]%d][1] = 1L;
      //not max
      for(int k = 0; k < array[0]; k++){
         dp[0][k%d][0] = (dp[0][k%d][0] + 1L + MOD)%MOD;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < d; j++){
            if(dp[k][j][0] > 0){
               //already not max, can add anything
               for(int h = 0; h <= 9; h++){
                  dp[k+1][(j+h)%d][0] = (dp[k+1][(j+h)%d][0] + dp[k][j][0] + MOD)%MOD;
               }
            }
            
            if(dp[k][j][1] > 0){
               //continue max
               dp[k+1][(j+array[k+1])%d][1] = (dp[k+1][(j+array[k+1])%d][1] + dp[k][j][1] + MOD)%MOD;
               //not max
               for(int h = 0; h < array[k+1]; h++){
                  dp[k+1][(j+h)%d][0] = (dp[k+1][(j+h)%d][0] + dp[k][j][1] + MOD)%MOD;
               }
            }
         }
      }
      
      long answer = (dp[n-1][0][0] + dp[n-1][0][1] -1 + MOD)%MOD;       //minus one because 0 doesn't count
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}