//make sure to make new file!
import java.io.*;
import java.util.*;
//eddie
public class D144b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         long[] psum = new long[n+1];
         psum[0] = 0L;
         long[] array = new long[n+1];
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
            psum[k] = psum[k-1] + array[k];
         }
         
         if(x == 0){
            //get maximum sum subarray
            long answer = maxsum(array,psum);
            out.println(answer);
         } else if(m == 0){
            for(int k = 1; k <= n; k++){
               array[k] -= x;
               psum[k] = psum[k-1] + array[k];
            }
            long answer = maxsum(array,psum);
            out.println(answer);
         } else {
         
            //dp[x][y] = max val of subarray ending in x after using y operations
            long answer = 0L;
            long[][] dp = new long[n+1][m+1];
            for(int k = 0; k <= n; k++) Arrays.fill(dp[k],-1);
            if(m < n){
               dp[1][0] = Math.max(0,array[1]-x);
               answer = Math.max(answer,dp[1][0]);
            }
            dp[1][1] = Math.max(0,array[1]+x);
            answer = Math.max(answer,dp[1][1]);
            
            for(int k = 1; k < n; k++){
               for(int j = 0; j <= m; j++){
                  if(dp[k][j] == -1) continue;
                  
                  //don't use
                  if(m-j < n-k){
                     dp[k+1][j] = Math.max(dp[k+1][j], Math.max(dp[k][j] + array[k+1]-x,0));
                     answer = Math.max(answer,dp[k+1][j]);
                  }
                  
                  //use
                  if(j < m){
                     dp[k+1][j+1] = Math.max(dp[k+1][j+1], Math.max(dp[k][j] + array[k+1]+x,0));
                     answer = Math.max(answer,dp[k+1][j+1]);
                  }
               
               }
            
            }
            
            out.println(answer);
         
         
         }
         
         

      }
      
      
      
      
      out.close();
   }
   
   public static long maxsum(long[] array, long[] psum){
      int n = array.length-1;
      long max = 0;
      long minpsum = 0;
      for(int k = 1; k <= n; k++){
         max = Math.max(max,psum[k]-minpsum);
         minpsum = Math.min(minpsum,psum[k]);
      }
      return max;
   }
      
}