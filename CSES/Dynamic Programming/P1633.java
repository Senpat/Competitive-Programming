//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1633{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] dp = new long[n+1];
      dp[0] = 1L;
      
      for(int k = 0; k < n; k++){
         for(int j = 1; j <= 6; j++){
            if(k+j <= n){
               dp[k+j] = (dp[k+j] + dp[k] + MOD)%MOD;
            }
         }
      }
      
      out.println(dp[n]);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}