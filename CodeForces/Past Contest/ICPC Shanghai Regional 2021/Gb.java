//make sure to make new file!
import java.io.*;
import java.util.*;

public class Gb{
   
   public static long MOD = 998244353L;
   public static long i2 = 499122177L;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[][] dp;
   
   public static long[] pairs;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 100005;
      
      //# of ways to make pairs with x choices
      pairs = new long[N];
      Arrays.fill(pairs,-1);
      
      pairs[0] = 1L;
      pairs[2] = 1L;
      pairs[4] = 3L;
      long numer = 6L;
      long denom = 2L;
      
      for(int k = 6; k < N; k+=2){
         long c2 = ((long)k * ((long)k - 1) + MOD)%MOD;
         c2 = (c2 * i2 + MOD)%MOD;
         numer = (numer * c2 + MOD)%MOD;
         denom = (denom * (k/2) + MOD)%MOD;
         pairs[k] = (numer*modInverse(denom,MOD) + MOD)%MOD;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      //first number of # of combinations, second number: 0 if must be even, 1 if must be odd
      dp = new long[n+1][2];
      
      dfs(1,-1);
      
      long answer = dp[1][0];
      out.println(answer);
      
      
      
      
      
      out.close();
   }


   public static void dfs(int v, int p){
      
      long prod = 1L;
      int numodd = 0;
      int numeven = 0;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         
         dfs(nei,v);
         
         prod = (prod * dp[nei][0] + MOD)%MOD;
         
         if(dp[nei][1] == 0) numeven++;
         else numodd++;
      }
      
      if(numeven % 2 == 0){
         dp[v][1] = 0;
         dp[v][0] = (prod * pairs[numeven] + MOD)%MOD;
      } else {
         dp[v][1] = 1;
         long temp = (prod * pairs[numeven-1] + MOD)%MOD;
         dp[v][0] = (temp * (long)numeven + MOD)%MOD;
      }
   }
   
   
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