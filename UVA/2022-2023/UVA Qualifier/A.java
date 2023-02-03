//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, danny hint
public class A{
   
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
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1]*(long)k)%MOD;
      }
      ifac[N-1] = power(fac[N-1], MOD-2);
      for(int i=N-2; i >= 1; i--){
         ifac[i] = (ifac[i+1]*(long)(i+1))%MOD;
      }
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken())-1;
         int y = Integer.parseInt(st.nextToken())-1;
         long ans = Long.parseLong(st.nextToken());
         
         x+=4;
         y+=2;
         
         long answer = choose(x,y);
         answer = ((answer - 2L*choose(x-2,y-1))%MOD + MOD)%MOD;
         answer = ((answer - choose(x-4,y-2))%MOD + MOD)%MOD;
         
         if(answer == ans){
            out.println("Correct");
         } else {
            out.println("Incorrect");
         }
      }
      
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      if(r > n) return 0L;
      if(n == r) return 1L;
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      prod = (prod * ifac[n-r] + MOD)%MOD;
      return prod;
   }
   
   public static long power(long base, long exp){
      if(exp == 0) return 1;
      if(exp == 1) return (base + MOD)%MOD;
      long ans = power(base,exp/2);
      ans = (ans*ans + MOD)%MOD;
      if(exp%2 == 1) ans = (ans*base + MOD)%MOD;
      
      return ans;   
   }
   
      
}