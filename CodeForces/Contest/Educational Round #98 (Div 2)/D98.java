//make sure to make new file!
import java.io.*;
import java.util.*;

public class D98{
   
   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] fibo = new long[n+1];
      fibo[0] = 0L;
      fibo[1] = 1L;
      
      for(int k = 2; k <= n; k++){
         fibo[k] = (fibo[k-1] + fibo[k-2] + MOD)%MOD;
      }
      
      long[] pow2 = new long[n+1];
      pow2[0] = 1L;
      for(int k = 1; k <= n; k++){
         pow2[k] = (pow2[k-1] * 2L + MOD)%MOD;
      }
      
      long answer = (fibo[n] * modInverse(pow2[n],MOD) + MOD)%MOD;
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0, x = 1; 
     
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