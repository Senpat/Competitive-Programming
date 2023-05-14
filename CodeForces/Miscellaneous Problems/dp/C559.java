//Gerald and Giant Chess
import java.io.*;
import java.util.*;
//blog
public class C559{
   
   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      fac = new long[N];
      ifac = new long[N];
      
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      ifac[N-1] = modInverse(fac[N-1],MOD);
      
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int h = Integer.parseInt(st.nextToken())-1;
      int w = Integer.parseInt(st.nextToken())-1;
      int n = Integer.parseInt(st.nextToken());
      
      Black[] blacks = new Black[n+1];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken())-1;
         int y = Integer.parseInt(st.nextToken())-1;
         
         blacks[k] = new Black(x,y);
      }
      blacks[n] = new Black(h,w);
      
      Arrays.sort(blacks);
      
      long[] dp = new long[n+1];
      for(int k = 0; k <= n; k++){
         dp[k] = choose(blacks[k].x + blacks[k].y,blacks[k].x);
         for(int j = 0; j < k; j++){
            //if blacks[j] is inside blacks[k]
            if(blacks[j].x <= blacks[k].x && blacks[j].y <= blacks[k].y){
               int dx = blacks[k].x - blacks[j].x;
               int dy = blacks[k].y - blacks[j].y;
               long prod = (dp[j] * choose(dx + dy,dx) + MOD)%MOD;
               dp[k] -= prod;
               if(dp[k] < 0) dp[k] += MOD;
            }
         }
      }
      
      out.println(dp[n]);
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r] + MOD)%MOD;
   }
   
   public static class Black implements Comparable<Black>{
      int x;
      int y;
      public Black(int a, int b){
         x = a;
         y = b;
      }
      public int compareTo(Black b){
         //sort by increasing x, then increasing y
         if(x == b.x) 
            return y-b.y;
         return x-b.x;
      }
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