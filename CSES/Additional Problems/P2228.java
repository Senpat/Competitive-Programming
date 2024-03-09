//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2228{

   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      
      fac = new long[N];
      ifac = new long[N];
      
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      ifac[N-1] = exp(fac[N-1],MOD-2);
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long ml = (long)m;
      
      
      long answer = 0L;
      
      for(int k = m; k >= 1; k--){
         long cur = (choose(m,k) * exp((long)k,n) + MOD)%MOD;
         if(k%2 == m%2){
            answer+=cur;
            if(answer >= MOD) answer -= MOD;
         } else {
            answer-=cur;
            if(answer < 0) answer += MOD;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r] + MOD)%MOD;
   }
   
   public static long exp(long base, long power){
      if(power == 0) return 1;
      if(power == 1) return base;
      long ans = exp(base,power/2);
      ans = (ans*ans + MOD) % MOD;
      if(power%2 == 1) ans = (ans*base + MOD) % MOD;
      return ans;
   }
   
      
}

