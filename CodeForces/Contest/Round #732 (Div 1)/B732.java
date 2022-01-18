//make sure to make new file!
import java.io.*;
import java.util.*;

public class B732{

   public static int N = 100005;
   public static long MOD = 998244353L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      fac = new long[N];
      ifac = new long[N];
      
      fac[0] = 1L;
      ifac[0] = 1L;
      
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
         ifac[k] = modInverse(fac[k],MOD);
      }
         
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         char[] array = f.readLine().toCharArray();
         
         int total = 0;
         int blocks = 0;
         
         int chain = 0;
         
         for(int k = 0; k < n; k++){
            if(array[k] == '0'){
               total++;
               
               total += chain/2;
               blocks += chain/2;
               
               chain = 0;
                  
            } else {
               chain++;
            }
         }
         
         total += chain/2;
         blocks += chain/2;
               
         long answer = choose(total,blocks);
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r])%MOD;
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