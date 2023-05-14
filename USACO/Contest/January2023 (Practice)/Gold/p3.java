//make sure to make new file!
import java.io.*;
import java.util.*;

public class p3{

   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int N = 1000005;
      fac = new long[N];
      ifac = new long[N];
      fac[0] = 1L;
      ifac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = ((long)k*fac[k-1] + MOD)%MOD;
      }
      ifac[N-1] = modInverse(fac[N-1],MOD);
      for(int k = N-2; k >= 1; k--){
         ifac[k] = ((long)(k+1) * ifac[k+1] + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      
      long answer = 1L;
      for(int k = 0; k < n-1; k++){
         //up-downs
         int ud1 = (array[k])/2;
         int ud2 = (array[k+1])/2;
         
         if(ud1 == 0 || ud2 == 0) continue;
         
         if(ud2 <= ud1){
            //fill as many ud1 with ud2
            answer = (answer * choose(ud1,ud2) + MOD)%MOD;
         } else {
            //stars and bars - every ud1 should have at least 1 ud2
            answer = (answer * choose(ud2-1,ud1-1) + MOD)%MOD;
         }
         
      }
      
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