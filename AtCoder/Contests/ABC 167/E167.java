//make sure to make new file!
import java.io.*;
import java.util.*;

public class E167{
   
   public static long MOD = 998244353L;
   public static long[] fac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      
      fac = new long[n+5];
      fac[0] = 1L;
      for(int k = 1; k < n+5; k++){
         fac[k] = (fac[k-1]*(long)k + MOD)%MOD;
      }
      
      //add 0
      long answer = (m*exp(m-1,n-1) + MOD)%MOD;
      
      for(int k = 1; k <= x; k++){
         long add = (m*exp(m-1,n-k-1) + MOD)%MOD;
         add = (add * choose(n-1,k) + MOD)%MOD;
         answer = (answer + add + MOD)%MOD;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long exp(long base, int power){
      if(power == 0) 
         return 1;
      if(power == 1) 
         return (base + MOD) % MOD;
      long ans = exp(base,power/2);
      ans = (ans*ans + MOD) % MOD;
      if(power%2 == 1) ans = (ans*base + MOD) % MOD;
      return (ans + MOD) % MOD;
   }
   
   public static long exp(long base, long power){
      if(power == 0) 
         return 1;
      if(power == 1) 
         return (base + MOD) % MOD;
      long ans = exp(base,power/2);
      ans = (ans*ans + MOD) % MOD;
      if(power%2 == 1) ans = (ans*base + MOD) % MOD;
      return (ans + MOD) % MOD;
   }
   
   public static long modInverse(long n){
      return exp(n,MOD-2);
   }
   
   public static long choose(int n, int r) { 
          
      if (r == 0) 
         return 1; 
      
      long ret = (fac[n]* modInverse(fac[r]) + MOD) % MOD;
      ret = (ret * modInverse(fac[n-r]) + MOD) % MOD;
      return ret;
      
        
   }
      
}