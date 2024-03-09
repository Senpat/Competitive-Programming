//make sure to make new file!
import java.io.*;
import java.util.*;

public class EH{
   
   public static long MOD = 998244353L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 5005;
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
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int min = 0;               //going to add a 0
         int max = 0;
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            min = Math.min(min,array[k]);
            max = Math.max(max,array[k]);
         }
         
         int fn = max-min+1;
         int[] freq = new int[fn];
         for(int k = 0; k < n; k++){
            freq[array[k]-min]++;
         }
         //add 0, must be the beginning
         int beginning = 0-min;
         freq[beginning]++;
         
         boolean fail = false;
         for(int k = 0; k < fn; k++){
            if(freq[k] == 0){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println(0);
            continue;
         }
         
         //fn (reduce memory), # available, added beginning, added end
         long[][][][] dp = new long[2][n+2][2][2];
         
         //no ends
         if(0 == beginning){
            dp[0][freq[0]][1][0] = 1L;
            if(freq[0] >= 2){
               dp[0][freq[0]][1][1] = 1L;
            }
         } else {
            dp[0][freq[0]][0][1] = 1L;
            dp[0][freq[0]][0][0] = 1L;
         }
         
         int gaps, newj;
         for(int k = 0; k < fn-1; k++){
            int cur = k&1;
            int to = 1-cur;
            
            int x = freq[k+1];
            for(int j = 1; j <= n; j++){
            
               //no ends
               gaps = j+1;
               
               newj = x - gaps + 1;
               if(x >= gaps){
                  //add no ends
                  dp[to][newj][0][0] = (dp[to][newj][0][0] + choose(x-1,gaps-1)*dp[cur][j][0][0] + MOD)%MOD;
                  //add beginning
                  if(k+1 == beginning){
                     dp[to][newj][1][0] = (dp[to][newj][1][0] + choose(x-1,gaps-1)*dp[cur][j][0][0] + MOD)%MOD;
                     
                     //add both beginning and end
                     dp[to][newj][1][1] = (dp[to][newj][1][1] + choose(x-1,gaps-1)*dp[cur][j][0][0] + MOD)%MOD;
                  }
                  //add end
                  dp[to][newj][0][1] = (dp[to][newj][0][1] + choose(x-1,gaps-1)*dp[cur][j][0][0] + MOD)%MOD;
               }
               
               //one end
               gaps = j;
               newj = x - gaps + 1;
               if(x >= gaps){
                  //beginning
                  //don't add end
                  dp[to][newj][1][0] = (dp[to][newj][1][0] + choose(x-1,gaps-1)*dp[cur][j][1][0] + MOD)%MOD;
                  //add end
                  dp[to][newj][1][1] = (dp[to][newj][1][1] + choose(x-1,gaps-1)*dp[cur][j][1][0] + MOD)%MOD;
                  
                  //end
                  //don't add beginning
                  dp[to][newj][0][1] = (dp[to][newj][0][1] + choose(x-1,gaps-1)*dp[cur][j][0][1] + MOD)%MOD;
                  if(k+1 == beginning){
                     //add beginning
                     dp[to][newj][1][1] = (dp[to][newj][1][1] + choose(x-1,gaps-1)*dp[cur][j][0][1] + MOD)%MOD;
                  }
               }
               
               //two ends
               gaps = j-1;
               newj = x - gaps + 1;
               if(x >= gaps && gaps >= 1){
                  dp[to][newj][1][1] = (dp[to][newj][1][1] + choose(x-1,gaps-1)*dp[cur][j][1][1] + MOD)%MOD;
               }
               
               //set to 0
               dp[cur][j][0][0] = 0L;
               dp[cur][j][1][0] = 0L;
               dp[cur][j][0][1] = 0L;
               dp[cur][j][1][1] = 0L;     
            }
            
         }
      
         long answer = dp[(fn-1)&1][1][1][1];
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   
   
   public static long choose(int n, int r){
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