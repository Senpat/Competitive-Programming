//Divan and Kostomuksha
import java.io.*;
import java.util.*;
//solves D2 - TLE
//semi-t+sol for count loop
//uses linear sieve
public class D1614c{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 20000005;
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      for(int k = 2; k < N; k++){
         if(!isprime[k]) 
            continue;
         for(int j = k*2; j < N; j+=k){
            isprime[j] = false;
         }
      }
      
      ArrayList<Integer> pr = new ArrayList<Integer>();
      for(int k = 2; k < N; k++) 
         if(isprime[k]) pr.add(k);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      
      long[] freq = new long[N+1];
      long[] count = new long[N+1];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         count[array[k]]++;
      }
      
      for(int prime : pr){
         for(int k = N/prime; k >= 1; k--){
            count[k] += count[k*prime];
         }
      }
    
      long[] dp = new long[N+1];
      for(int k = 1; k <= N; k++){
         dp[k] = (long)k * count[k];
      }
      
      for(int k = N; k >= 1; k--){
         long kl = (long)k;
         for(int p : pr){
            if(p*k > N) break;
            dp[k] = Math.max(dp[k],dp[k*p] + kl * (count[k]-count[k*p]));
         }
      }
      
      long answer = 0L;
      for(int k = 1; k <= N; k++){
         answer = Math.max(answer,dp[k]);
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}