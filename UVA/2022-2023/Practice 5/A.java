//Little Toys
import java.io.*;
import java.util.*;
//Burnside's Lemma
//upsolve
public class A{
   
   public static long MOD = 1000000007L;
   
   public static int P = 1000000;
   public static long[] p2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      p2 = new long[P];
      p2[0] = 1L;
      
      for(int k = 1; k < P; k++){
         p2[k] = (2L * p2[k-1] + MOD)%MOD;
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int qq = Integer.parseInt(st.nextToken());
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         ArrayList<Long> adivisors = new ArrayList<Long>();
         ArrayList<Long> bdivisors = new ArrayList<Long>();
         
         for(long x = 1L; x*x <= a; x++){
            if(a % x == 0){
               adivisors.add(x);
               adivisors.add(a/x);
            }
         }
         
         for(long x = 1L; x*x <= b; x++){
            if(b % x == 0){
               bdivisors.add(x);
               bdivisors.add(b/x);
            }
         }
         
         Collections.sort(adivisors);
         Collections.sort(bdivisors);
         
         int an = adivisors.size();
         int bn = bdivisors.size();
         
         //a.get(x) stores the indices in a of the divisors that are a multile of adivisors.get(x)
         ArrayList<ArrayList<Integer>> amuls = new ArrayList<ArrayList<Integer>>(an);
         ArrayList<ArrayList<Integer>> bmuls = new ArrayList<ArrayList<Integer>>(bn);
         
         for(int k = 0; k < an; k++){
            ArrayList<Integer> muls = new ArrayList<Integer>();
            for(int j = k+1; j < an; j++){
               if(adivisors.get(j)%adivisors.get(k) == 0L){
                  muls.add(j);
               }
            }
            amuls.add(muls);
         }
         
         
         for(int k = 0; k < bn; k++){
            ArrayList<Integer> muls = new ArrayList<Integer>();
            for(int j = k+1; j < bn; j++){
               if(bdivisors.get(j)%bdivisors.get(k) == 0L){
                  muls.add(j);
               }
            }
            bmuls.add(muls);
         }
         
         long answer = 0L;
         
         long[] afreq = new long[an];
         
         for(int ai = an-1; ai >= 0; ai--){
            long curafreq = a/adivisors.get(ai);
            for(int mul : amuls.get(ai)){
               curafreq -= afreq[mul];
            }
            
            long cur = 0L;
            
            long[] bfreq = new long[bn];
            for(int bi = bn-1; bi >= 0; bi--){
               long curbfreq = b/bdivisors.get(bi);
               for(int mul : bmuls.get(bi)){
                  curbfreq -= bfreq[mul];
               }
               
               //doesn't overflow I'm pretty sure
               //equivalent to (a*b)/lcm(a/x,b/y)
               long pow = adivisors.get(ai) * bdivisors.get(bi) * gcd(a/adivisors.get(ai),b/bdivisors.get(bi));
               
               long prod = (pow2(pow) * curbfreq + MOD)%MOD;
               
               cur = (cur + prod + MOD)%MOD;
               bfreq[bi] = curbfreq;
            }
            
            long prod = (cur*curafreq + MOD)%MOD;
            answer = (answer + prod + MOD)%MOD;
            afreq[ai] = curafreq;
            
            
         }
         
         answer = (answer * exp((a*b + MOD)%MOD,MOD-2) + MOD)%MOD;
         
         out.println(q + " " + answer);
      
      }
      
      
      
      
      out.close();
   }
   
   public static long gcd(long a, long b){
      if(a > b){
         if(b == 0L) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0L) return b;
         return gcd(b%a,a);
      }
      return a;
   }
   
   public static long pow2(long x){
      if(x < (long)P) 
         return p2[(int)x];
      return exp(2L,x);
   }
   
   public static long exp(long base, long power){
      if(power == 0L) 
         return 1L;
      if(power == 1L) 
         return base;
      long ans = exp(base,power/2L);
      ans = (ans*ans + MOD)%MOD;
      if(power%2 == 1) ans = (ans*base + MOD)%MOD;
      return ans;
   }
      
}