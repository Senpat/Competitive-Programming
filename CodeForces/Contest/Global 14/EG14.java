//make sure to make new file!
import java.io.*;
import java.util.*;

public class EG14{

   public static long MOD;
   public static int MAX = 405;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      MOD = Long.parseLong(st.nextToken());
      
      long[] pow2 = new long[MAX];
      pow2[0] = 1L;
      for(int k = 1; k < MAX; k++){
         pow2[k] = (2L*pow2[k-1] + MOD)%MOD;
      }
      
      fac = new long[MAX];
      ifac = new long[MAX];
      fac[0] = 1L;
      ifac[0] = 1L;
      for(int k = 1; k < MAX; k++){
         fac[k] = ((long)k*fac[k-1] + MOD)%MOD;
         ifac[k] = modInverse(fac[k],MOD);
      }
      
      long[][] dp = new long[n][n+1];                 //what n you're on, what how many computers you've turned on manually
      
      //initial
      for(int k = 0; k < n; k++){
         dp[k][k+1] = pow2[k];
      }
      
      for(int k = 2; k < n; k++){
         for(int j = 1; j <= n; j++){
            if(dp[k-2][j-1] == 0) continue;
            long start = dp[k-2][j-1];                //number for part up to previous block
            
            for(int add = 1; ; add++){
               if(k+add-1 >= n || j+add-1 > n) break;
               
               long adder = (start * pow2[add-1] + MOD)%MOD;
               adder = (adder * choose(j+add-1,j-1) + MOD)%MOD;
               dp[k+add-1][j+add-1] = (dp[k+add-1][j+add-1] + adder + MOD)%MOD;
            }
         }
      }
      
      long answer = 0L;
      for(int k = 1; k <= n; k++){
         answer = (answer + dp[n-1][k] + MOD)%MOD;
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   //a choose b
   public static long choose(int a, int b){
      long prod = (fac[a]*ifac[b] + MOD)%MOD;
      return (prod*ifac[a-b] + MOD)%MOD;
   }
   
      //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0L;
       long x = 1L; 
     
       if (m == 1L) 
         return 0L; 
     
       while (a > 1L) 
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
       if (x < 0L) 
          x += m0; 
     
       return x; 
   } 
      
}