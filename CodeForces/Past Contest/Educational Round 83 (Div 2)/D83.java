//make sure to make new file!
import java.io.*;
import java.util.*;

public class D83{

   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      
      long answer = nCrModPFermat(m,n-1,MOD);
      answer = (answer * (n-2) + MOD)%MOD;
      
      
      long pow2 = 1L;
      for(int k = 1; k <= n-3; k++){
         pow2 = (pow2*2+MOD)%MOD;
      }
      
      answer = (answer * pow2 + MOD)%MOD;
      
      out.println(answer); 
      
   
      
      
      
      
      
      out.close();
   }
   
   /* Iterative Function to calculate 
    (x^y)%p in O(log y) FROM GEEKS FOR GEEKS  */
   public static long power(long x, long y, long p) 
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
   public static long modInverse(long n, long p) 
   { 
      return power(n, p-2, p); 
   } 
      
    // Returns nCr % p using Fermat's 
    // little theorem. 
   public static long nCrModPFermat(long n, long r, 
                                    long p) 
   { 
          
        // Base case 
      if (r == 0) 
         return 1; 
      
        // Fill factorial array so that we 
        // can find all factorial of r, n 
        // and n-r 
      long[] fac = new long[(int)n+1]; 
      fac[0] = 1; 
          
      for (int i = 1 ;i <= n; i++) 
         fac[i] = fac[i-1] * i % p; 
      
      return (fac[(int)n]* modInverse(fac[(int)r], p) 
                % p * modInverse(fac[(int)(n-r)], p) 
                                    % p) % p; 
   } 
      

} 
      
