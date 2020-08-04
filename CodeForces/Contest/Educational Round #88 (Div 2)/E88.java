//make sure to make new file!
import java.io.*;
import java.util.*;

public class E88{
   
   public static long MOD = 998244353L;
   public static long[] fac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      fac = new long[500005];
      fac[0] = 1L;
      for(int k = 1; k < 500005; k++){
         fac[k] = (fac[k-1]*(long)k + MOD)%MOD;
      }
      
      long answer = 0L;
      for(long k = 1; k <= n; k++){
         long next = k*2;
      
         long num = (n-next)/k+1;
         //if(n%k == 0) num++;
         if(num < m-1) 
            break;
         //out.println(num);
         answer = (answer + choose(num,m-1) + MOD) % MOD;
      }
      
      out.println(answer);
      
      
      out.close();
   }
   /* Iterative Function to calculate 
    (x^y)%p in O(log y) */
   static long power(long x, long y, long p) 
   { 
          
        // Initialize result 
      long res = 1; 
      
        // Update x if it is more than or 
        // equal to p 
      x = x % p; 
                      
      while (y > 0) 
      { 
              
            // If y is odd, multiply x 
            // with result 
         if (y % 2 == 1) 
            res = (res * x) % p; 
      
            // y must be even now 
         y = y >> 1; // y = y/2 
         x = (x * x) % p; 
      } 
          
      return res; 
   } 
      
    // Returns n^(-1) mod p 
   static long modInverse(long n, long p) 
   { 
      return power(n, p-2, p); 
   } 
      
    // Returns nCr % p using Fermat's 
    // little theorem. 
   static long choose(long n, long r) 
   { 
      if (r == 0) 
         return 1; 
      
      
      return (((fac[(int)n] * modInverse(fac[(int)r], MOD) + MOD) % MOD) * modInverse(fac[(int)(n-r)], MOD) + MOD) % MOD; 
   } 
      
}