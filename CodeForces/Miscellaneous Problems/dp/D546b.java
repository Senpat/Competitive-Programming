//Soldier and Number Game
//semi
import java.io.*;
import java.util.*;

public class D546b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] queries = new int[n][2];
      
      int max = 0;
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         queries[k][0] = Integer.parseInt(st.nextToken());
         queries[k][1] = Integer.parseInt(st.nextToken());
         max = Math.max(max, queries[k][0]);
      }
      
      //sieve
      int[] sieve = new int[max+1];
      sieve[1] = 0;                          
      for(int k = 2; k < max+1; k++){
         for(int i = k*2; i < max+1; i+=k){
            if(sieve[i] == 0) sieve[i] = k;
         }
      }
      
      int[] dp = new int[max+1];
      for(int k = 2; k < dp.length; k++){
         if(sieve[k] == 0) dp[k] = 1;
         else {
            dp[k] = dp[k/sieve[k]]+1;
         }
      }
      
      int[] pref = new int[dp.length];
      for(int k = 1; k < dp.length; k++){
         pref[k] = pref[k-1] + dp[k];
      }
      
      for(int k = 0; k < n; k++){
         out.println(pref[queries[k][0]] - pref[queries[k][1]]);
      }
      
      
      out.close();
   
   }

}