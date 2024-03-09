//Hyperregular Bracket Strings
import java.io.*;
import java.util.*;
//tutorial
public class C1830c{

   public static long MOD = 998244353L;

   public static long[] fac;
   public static long[] ifac;
   public static long[] catalan;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 300005;
      
      fac = new long[N];
      ifac = new long[N];
      catalan = new long[N];
      
      fac[0] = 1L;
      
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      ifac[N-1] = modInverse(fac[N-1],MOD);
      
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      for(int k = 2; k < 300001; k += 2){
         int i = k/2;
         catalan[k] = (modInverse(2*i + 1, MOD) * choose(2*i + 1,i) + MOD)%MOD;
      }
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long[] diff = new long[n+2];
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            long hash = (long)(Math.random() * Long.MAX_VALUE);
            
            diff[l] ^= hash;
            diff[r+1] ^= hash;
         }
         
         HashMap<Long,Integer> hmap = new HashMap<Long,Integer>();
         
         long xor = 0L;
         for(int k = 1; k <= n; k++){
            xor ^= diff[k];
            
            if(hmap.containsKey(xor)){
               hmap.put(xor,hmap.get(xor)+1);
            } else {
               hmap.put(xor,1);
            }
         }
         
         long answer = 1L;
         
         for(long i : hmap.keySet()){
            answer = (answer * catalan[hmap.get(i)] + MOD)%MOD;
         }
         
         out.println(answer);
      
      }
      
      
      
      
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