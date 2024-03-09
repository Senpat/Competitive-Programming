//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial
public class E{

   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      fac = new long[N];
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      ifac = new long[N];
      ifac[N-1] = modInverse(fac[N-1],MOD);
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int x = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      //if lca = a*g, x = g * (1+a)
      long answer = 0L;
      //get all divisors
      int i = 1;
      while(i*i <= x){
         
         if(x%i == 0){
            if(i > 1){
               answer = (answer + solve(i-1,m) + MOD)%MOD;
            }
            answer = (answer + solve(x/i - 1,m) + MOD)%MOD;
         }
         i++;
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   //finds the number of sets of m numbers such that gcd is 1 and lcm is a
   public static long solve(int a, int m){
   
      //prime factorization exponents
      ArrayList<Long> pfe = new ArrayList<Long>();
      int i = 2;
      int ai = a;
      while(i*i <= ai){
         long count = 0;
         while(ai % i == 0){
            count++;
            ai/=i;
         }
         
         if(count > 0){
            pfe.add(count);
         }
         
         i++;
      }
      
      if(ai > 1){
         pfe.add(1L);
      }
      
      //p is at most 9
      int p = pfe.size();
      
      long answer = 0L;
      
      int p2 = (1 << (2*p));
      //pie on # of restrictions broken
      for(int mask = 0; mask < p2; mask++){
         int num1 = 0;
         
         int cur = 1;
         //one restriction is 2*b, other is 2*b+1
         for(int b = 0; b < p; b++){
            int sub = 0;
            sub += (mask >> (2*b))&1;
            num1 += (mask >> (2*b))&1;
            sub += (mask >> (2*b+1))&1;
            num1 += (mask >> (2*b+1))&1;
            
            cur *= (pfe.get(b)+1-sub);
         }
         
         //cur is at most # of divisors of a, which is <= 2*sqrt(a)
         if(num1 % 2 == 0){
            answer += choose(cur,m);
            if(answer >= MOD) answer -= MOD;
         } else {
            answer -= choose(cur,m);
            if(answer < 0) answer += MOD;
         }
      }
      //System.out.println(a + " " + answer);
      return answer;
   }
   
   
   public static long choose(int n, int r){
      if(r > n) return 0L;
      long ret = (fac[n] * ifac[r] + MOD)%MOD;
      return (ret * ifac[n-r] + MOD)%MOD;
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