//make sure to make new file!
import java.io.*;
import java.util.*;

public class B680{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         Long[] array = new Long[2*n];
         for(int k = 0;  k < 2*n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         long sumbot = 0L;
         long sumtop = 0L;
         
         for(int k = 0; k < n; k++){
            sumbot = (sumbot+array[k] + MOD)%MOD;
            sumtop = (sumtop+array[k+n] + MOD)%MOD;
         }
         
         long dif = ((sumtop - sumbot + MOD)%MOD + MOD)%MOD;
         
         long[] fac = new long[2*n+1];
         long[] ifac = new long[2*n+1];
         
         fac[0] = 1L;
         ifac[0] = 1L;
         
         for(int k = 1; k <= 2*n; k++){
            fac[k] = (fac[k-1]*((long)k) + MOD)%MOD;
            ifac[k] = modInverse(fac[k],MOD);
         }
         
         long n2cn = (fac[2*n]*ifac[n] + MOD)%MOD;
         n2cn = (n2cn * ifac[n] + MOD)%MOD;
         
         long answer = (dif * n2cn + MOD)%MOD;
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