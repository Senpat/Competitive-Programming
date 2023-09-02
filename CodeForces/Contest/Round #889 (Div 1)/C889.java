//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class C889{

   public static long MOD = 1000000007L;
   public static long i2 = 500000004L;
   
   public static long[] fac;
   public static long[] ifac;
   public static long[] ipow2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1005;
      ipow2 = new long[N];
      ipow2[0] = 1L;
      fac = new long[N];
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         ipow2[k] = (ipow2[k-1] * i2 + MOD)%MOD;
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      ifac = new long[N];
      ifac[N-1] = modInverse(fac[N-1],MOD);
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      long[] c2 = new long[501];
      for(int k = 0; k < 501; k++){
         c2[k] = choose(k*2,k);
      }
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken())+1;
         
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
         
      long answer = 0L;
         
      for(int k = 0; k < n-1; k++){
         int a = array[k+1]-array[k];
         int b = m-array[k+1];
            
         long[] pre = new long[b];
         long sumprob = 0L;
         for(int j = a; j < a+b; j++){
            long numer = choose(j+j-a,j-a);
            for(int h = 0; h < j-a; h++){
               numer = ((numer-pre[h]*c2[j-a-h])%MOD + MOD)%MOD;
            }
            pre[j-a] = numer;
            long prod = (numer * ipow2[j+j-a] + MOD)%MOD;
            answer = (answer + prod * (long)j + MOD)%MOD;
            
            sumprob = (sumprob + prod + MOD)%MOD;
            //out.println(answer);
         }
            
         //never cross
         long prob = ((1-sumprob)%MOD + MOD)%MOD;
         answer = (answer + prob * (long)(m-array[k]) + MOD)%MOD;
         
         //out.println(answer);
      }
      
      answer = (answer + (long)(m-array[n-1]) + MOD)%MOD;
      
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