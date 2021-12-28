//make sure to make new file!
import java.io.*;
import java.util.*;

public class E738{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] l = new int[n];
      int[] r = new int[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         l[k] = a;
         r[k] = b;
      }
      
      //sieve
      boolean[] isprime = new boolean[m+1];
      Arrays.fill(isprime,true);
      for(int k = 2; k <= m; k++){
         if(!isprime[k]) 
            continue;
         for(int j = 2*k; j <= m; j += k){
            isprime[j] = false;
         }
      }
      
      ArrayList<Integer> primes = new ArrayList<Integer>();
      for(int k = 2; k <= m; k++){
         if(isprime[k]) primes.add(k);
      }
      
      int[] primepar = new int[m+1];
      for(int k = 2; k <= m; k++){
         if(isprime[k]){
            primepar[k] = 1;
            if(k < 1000){                                         //prevent int overflow
               for(int j = k*k; j <= m; j+=(k*k)){
                  primepar[j] = -1;
               }
            }
            for(int j = k*2; j <= m; j+=k){
               if(primepar[j] != -1) primepar[j]++;
            }
         }
      }
      
      long answer = 0L;
      for(int k = 1; k <= m; k++){
         if(primepar[k] == -1) 
            continue;
         
         int[] curl = new int[n];
         int[] curr = new int[n];
         
         //divide everything
         int curm = m/k;                  //m is floor
         
         for(int j = 0; j < n; j++){
            curl[j] = (l[j]+k-1)/k;       //left is ceiling
            curr[j] = r[j]/k;             //right is floor
         }
      
         //dp
         long[][] dp = new long[n][curm+1];            //dp[k][j] = # of ways to get the sum of j with first k stars
         
         long[] psums = new long[curm+2];
         
         //do n = 1
         for(int j = curl[0]; j <= curr[0]; j++){
            dp[0][j] = 1L;
         }
         for(int j = 1; j <= curm+1; j++){
            psums[j] = psums[j-1] + dp[0][j-1];
         }
         
         for(int j = 1; j < n; j++){
            for(int h = 1; h <= curm; h++){
               dp[j][h] = (psums[Math.max(0,h-curl[j] +1)] - psums[Math.max(0,h-curr[j])] + MOD)%MOD;
            }
            
            //update prefix sums
            for(int h = 1; h <= curm+1; h++){
               psums[h] = (psums[h-1] + dp[j][h-1] + MOD)%MOD;
            }
         }
         
         long cursum = 0L;
         for(int j = 1; j <= curm; j++){
            cursum = (cursum + dp[n-1][j] + MOD)%MOD;
         }
         
         if(primepar[k] % 2 == 0){
            answer = (answer + cursum + MOD)%MOD;
         } else {
            answer = (answer - cursum + MOD)%MOD;
         }
         
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}