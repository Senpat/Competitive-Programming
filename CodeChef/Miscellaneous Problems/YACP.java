//make sure to make new file!
import java.io.*;
import java.util.*;

class YACP{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         int np2 = (1 << n);
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         long[][] dp = new long[n][np2];
         dp[0][0] = 1L;
         dp[0][1] = 1L;
         for(int k = 1; k < n; k++){
            for(int mask = 0; mask < (1 << ((k-1)+1)); mask++){
               //don't add it
               dp[k][mask] = (dp[k][mask] + dp[k-1][mask] + MOD)%MOD;
               //add it to new sequence
               dp[k][mask ^ (1 << k)] = (dp[k][mask ^ (1 << k)] + dp[k-1][mask] + MOD)%MOD;
               for(int j = 0; j < k; j++){
                  if((mask & (1 << j)) != 0 && array[k] > array[j]){
                     dp[k][mask ^ (1 << j) ^ (1 << k)] = (dp[k][mask ^ (1 << j) ^ (1 << k)] + dp[k-1][mask] + MOD)%MOD;
                  }
               }
            }
         }
         
         long answer = 0L;
         for(int mask = 0; mask < np2; mask++){
            answer = (answer + dp[n-1][mask] + MOD)%MOD;
         }
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}