//make sure to make new file!
import java.io.*;
import java.util.*;
//solves hard version
public class D767b{

   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int N = 1000005;
      fac = new long[N];
      ifac = new long[N];
      fac[0] = 1L;
      ifac[0] = 1L;
      
      long[] pow2 = new long[N];
      long[] ipow2 = new long[N];
      pow2[0] = 1L;
      ipow2[0] = 1L;
      
      for(int k = 1; k < N; k++){
         pow2[k] = (pow2[k-1]*2L + MOD)%MOD;
         ipow2[k] = modInverse(pow2[k],MOD);
         
         fac[k] = (fac[k-1]*k + MOD)%MOD;
         ifac[k] = modInverse(fac[k],MOD);
      }
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         if(n==m){
            out.println((x*n + MOD)%MOD);
            continue;
         }
         long answer = 0L;
         
         int cr = 0;
         int cn = n-m-1;
         
         for(int k = m; k >= 1; k--){
            long prod1 = (x*k + MOD)%MOD;
            long prod2 = (prod1*choose(cn,cr) + MOD)%MOD;  
            long div = (prod2*ipow2[n-k] + MOD)%MOD;
            answer = (answer + div + MOD)%MOD;
            
            cn++;
            cr++;
         }
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n]*ifac[r] + MOD)%MOD;
      return (prod*ifac[n-r] + MOD)%MOD;
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