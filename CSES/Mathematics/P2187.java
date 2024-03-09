//make sure to make new file!
import java.io.*;
import java.util.*;
//generalize proof from https://brilliant.org/wiki/catalan-numbers/
public class P2187{
   
   public static long MOD = 1000000007L;
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      fac = new long[N];
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      ifac = new long[N];
      ifac[N-1] = modInverse(fac[N-1],MOD);
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      
      int pref = 0;
      boolean fail = false;
      for(int k = 0; k < array.length; k++){
         if(array[k] == '(') pref++;
         else pref--;
         
         if(pref < 0) fail = true;      
      }
      
      if(fail || pref > n-array.length){
         out.println("0");
         out.close();
         return;
      }
      
      n -= array.length;
      
      int z = (n - pref)/2;
      
      long sum1 = choose(n,z);
      long sum2 = choose(n,z+pref+1);
      
      long answer = ((sum1 - sum2)%MOD + MOD)%MOD;
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   
   public static long choose(int n, int r){
      if(r > n) return 0L;
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