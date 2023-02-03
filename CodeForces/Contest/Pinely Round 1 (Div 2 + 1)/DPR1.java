//make sure to make new file!
import java.io.*;
import java.util.*;

public class DPR1{

   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   public static long[] pow3; 
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 2000005;
      
      fac = new long[N];
      ifac = new long[N];
      pow3 = new long[N];
      
      fac[0] = 1L;
      pow3[0] = 1L;
      
      for(int k = 1; k < N; k++){
         fac[k] = ((long)k * fac[k-1] + MOD)%MOD;
         pow3[k] = (3L * pow3[k-1] + MOD)%MOD;
      }
      
      ifac[N-1] = modInverse(fac[N-1],MOD);
      
      for(int k = N-2; k >= 0; k--){
         ifac[k] = ((long)(k+1) * ifac[k+1] + MOD)%MOD;
      }
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(m == 0){
         out.println(pow3[n]);
         out.close();
         return;
      }
      
      long answer = 0L;
      for(int s = 1; s <= m; s++){
         //case 1: msb is part of a segment
         int rb = m+s-1;         //required bits
         if(rb <= n){
            long prod = pow3[m-s];
            prod = (prod * pow3[n-rb] + MOD)%MOD;
            prod = (prod * choose(m-1,s-1) + MOD)%MOD;
            prod = (prod * choose(n-rb+s-1,s-1) + MOD)%MOD;
            
            answer = (answer + prod + MOD)%MOD;
         }
         
         //msb is not part of segment
         rb = m+s;
         if(rb <= n){
            long prod = pow3[m-s];
            prod = (prod * pow3[n-rb] + MOD)%MOD;
            prod = (prod * choose(m-1,s-1) + MOD)%MOD;
            prod = (prod * choose(n-rb+s,s) + MOD)%MOD;
            
            answer = (answer + prod + MOD)%MOD;
         }
      
      }
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   
   public static long choose(int n, int r){
      long prod1 = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod1 * ifac[n-r] + MOD)%MOD;
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