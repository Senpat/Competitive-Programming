//make sure to make new file!
import java.io.*;
import java.util.*;

public class E842{

   public static long MOD;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      MOD = Long.parseLong(st.nextToken());
      
      fac = new long[3*(n+1)];
      ifac = new long[3*(n+1)];
      
      fac[0] = 1L;
      for(int k = 1; k < 3*(n+1); k++){
         fac[k] = ((long)k * fac[k-1] + MOD)%MOD;
      }
      
      ifac[3*(n+1)-1] = modInverse(fac[3*(n+1)-1],MOD);
      
      for(int k = 3*(n+1)-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      //sorted
      long zero = 1L;
      
      //upper or lower 3rd is sorted
      long one = ((fac[2*n] + fac[2*n] - fac[n] - zero)%MOD + MOD)%MOD;
      
      //upper 2/3 has upper 1/3 or lower 2/3 has lower 1/3
      long uandl = (choose(2*n,n) * fac[n] + MOD)%MOD;
      uandl = (uandl * fac[2*n] + MOD)%MOD;
      uandl = (uandl * 2L + MOD)%MOD;

      //both calculation
      //pick how much of lower third is in the middle
      long facn3 = (fac[n] * fac[n] + MOD)%MOD;
      facn3 = (facn3 * fac[n] + MOD)%MOD;
      long both = 0L;
      for(int k = 0; k <= n; k++){
         long cnk = choose(n,k);
         long prod = (facn3 * cnk + MOD)%MOD;
         prod = (prod * cnk + MOD)%MOD;
         prod = (prod * choose(2*n-k,n) + MOD)%MOD;
         both = (both + prod + MOD)%MOD;
      }  

      long two = ((uandl-both)%MOD + MOD)%MOD;
      two = ((two - one - zero)%MOD + MOD)%MOD;
      
      long t123 = (zero + one + two + MOD)%MOD;
      //threes - the rest
      long three = ((fac[3*n] - t123)%MOD + MOD)%MOD;
      
      long answer = (one + two*2L + MOD)%MOD;
      answer = (answer + three*3L + MOD)%MOD;
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r] + MOD)%MOD;
   }
   
   
        //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   }  

      
}