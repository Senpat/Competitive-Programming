//make sure to make new file!
import java.io.*;
import java.util.*;
//O(N^2 * 18*2*18*2) -> 10^8
public class p3{

   public static long MOD = 1000000007L;
   public static int n;
   public static int[] array;
   
   public static long[] pow2;
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 400;
      pow2 = new long[N];
      fac = new long[N];
      ifac = new long[N];
      pow2[0] = 1L;
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         pow2[k] = (pow2[k-1] * 2L + MOD)%MOD;
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      ifac[N-1] = modInverse(fac[N-1],MOD);
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      long[][] answer = new long[n][n];
      
      long[][] calc1 = calc(b);
      long[][] calc2 = calc(a-1);
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            answer[k][j] = calc1[k][j]-calc2[k][j];
            if(answer[k][j] < 0) answer[k][j] += MOD;
         }
      }
      
      
      int q = Integer.parseInt(f.readLine());
      
      StringJoiner sj = new StringJoiner("\n");
      for(int qq = 0; qq < q; qq++){
         st = new StringTokenizer(f.readLine());
         int ql = Integer.parseInt(st.nextToken())-1;
         int qr = Integer.parseInt(st.nextToken())-1;
         
         sj.add("" + answer[ql][qr]);
      }
      
      out.println(sj.toString());
      
      
      out.close();
   }
   
   // counts # of ways to get <= x
   public static long[][] calc(long x){
      String xs = "" + x;
      int m = xs.length();
      int[] digits = new int[m];
      for(int k = 0; k < m; k++){
         digits[k] = Character.getNumericValue(xs.charAt(k));
      }
      
      //precompute less digits
      long[] lessdigits = new long[n+1];
      for(int k = 1; k <= n; k++){
         int size = Math.min(m-1,k);
         for(int len = 1; len <= size; len++){
            //number of ways to create a number of length len from a size k
            
            lessdigits[k] = (lessdigits[k] + choose(k,len) * pow2[len] + MOD)%MOD;
         }
      } 
      
      long[][] ret = new long[n][n];
      
      if(x == 0) return ret;
      
      for(int r = 0; r < n; r++){
         long[][][][] dp = new long[m+1][m+1][2][2];           //top used, bottom used, top type, bottom type
         //top type: 0 for top <, 1 for top ==
         //bottom type: 0 for bottom <=, 1 for bottom >
         //count for following (top, bottom) pairs: (0,0) (0,1), (1,0)
         
         //initial
         dp[0][0][1][0] = 1L;
         
         for(int l = r; l >= 0; l--){
            //fill ret[l][r]
            long[][][][] next = new long[m+1][m+1][2][2];
            
            for(int top = 0; top <= m; top++){
               for(int bot = 0; bot <= m-top; bot++){
                  //don't add number
                  next[top][bot][0][0] = add(next[top][bot][0][0] + dp[top][bot][0][0]);
                  next[top][bot][1][0] = add(next[top][bot][1][0] + dp[top][bot][1][0]);
                  next[top][bot][0][1] = add(next[top][bot][0][1] + dp[top][bot][0][1]);
                  next[top][bot][1][1] = add(next[top][bot][1][1] + dp[top][bot][1][1]);
                  
                  if(top + bot >= m) continue;
                  if(top + bot >= r-l+1) continue;
                  //add to top
                  int topdigit = digits[top];
                  
                  if(array[l] < topdigit){
                     next[top+1][bot][0][0] = add(next[top+1][bot][0][0] + dp[top][bot][0][0] + dp[top][bot][1][0]);
                     next[top+1][bot][0][1] = add(next[top+1][bot][0][1] + dp[top][bot][0][1] + dp[top][bot][1][1]);
                  } else if(array[l] == topdigit){
                     next[top+1][bot][0][0] = add(next[top+1][bot][0][0] + dp[top][bot][0][0]);
                     next[top+1][bot][0][1] = add(next[top+1][bot][0][1] + dp[top][bot][0][1]);
                     
                     next[top+1][bot][1][0] = add(next[top+1][bot][1][0] + dp[top][bot][1][0]);
                     next[top+1][bot][1][1] = add(next[top+1][bot][1][1] + dp[top][bot][1][1]);   
                  }
                  
                  //add to bottom
                  int botdigit = digits[m-1-bot];
                  
                  if(array[l] > botdigit){
                     next[top][bot+1][0][1] = add(next[top][bot+1][0][1] + dp[top][bot][0][0] + dp[top][bot][0][1]);
                     next[top][bot+1][1][1] = add(next[top][bot+1][1][1] + dp[top][bot][1][0] + dp[top][bot][1][1]);
                  } else if(array[l] == botdigit){
                     next[top][bot+1][0][0] = add(next[top][bot+1][0][0] + dp[top][bot][0][0]);
                     next[top][bot+1][0][1] = add(next[top][bot+1][0][1] + dp[top][bot][0][1]);
                     next[top][bot+1][1][0] = add(next[top][bot+1][1][0] + dp[top][bot][1][0]);
                     next[top][bot+1][1][1] = add(next[top][bot+1][1][1] + dp[top][bot][1][1]);
                  } else {    //array[l] < botdigit
                     next[top][bot+1][0][0] = add(next[top][bot+1][0][0] + dp[top][bot][0][0] + dp[top][bot][0][1]);
                     next[top][bot+1][1][0] = add(next[top][bot+1][1][0] + dp[top][bot][1][0] + dp[top][bot][1][1]);
                  }
               }
            }
            
            for(int top = 0; top <= m; top++){
               int bot = m-top;
               ret[l][r] = add(ret[l][r] + next[top][bot][0][0] + next[top][bot][0][1] + next[top][bot][1][0]);
            }
            ret[l][r] = add(ret[l][r] + lessdigits[r-l+1]);
            
            dp = next;
         }
      }
      
      return ret;
   }
   
   public static long add(long x){
      while(x >= MOD) x -= MOD;
      return x;
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