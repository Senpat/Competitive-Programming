//make sure to make new file!
import java.io.*;
import java.util.*;
//solves D1 hopefully
public class D854a{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
      
         long[] cold = new long[m+1];
         long[] hot = new long[m+1];
      
         st = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         for(int k = 1; k <= m; k++){
            cold[k] = Long.parseLong(st.nextToken());
            hot[k] = Long.parseLong(st2.nextToken());
         }
      
      //dp[x][y] = minimum time to do first x where one cpu last did x-1 and the other last did y
      //y = 0 means other cpu has never been run
         long[][] dp = new long[n][m+1];
         for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MAX_VALUE);
      
         dp[0][0] = cold[array[0]];
      
         for(int k = 0; k < n-1; k++){
            for(int j = 0; j <= m; j++){
               if(dp[k][j] == Long.MAX_VALUE) 
                  continue;
            //use same cpu as last time
               if(array[k] == array[k+1]){
                  dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j] + hot[array[k+1]]);
               } else {
                  dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j] + cold[array[k+1]]);
               }
            
            //use other cpu
               if(j == array[k+1]){
                  dp[k+1][array[k]] = Math.min(dp[k+1][array[k]],dp[k][j] + hot[array[k+1]]);
               } else {
                  dp[k+1][array[k]] = Math.min(dp[k+1][array[k]],dp[k][j] + cold[array[k+1]]);
               }
            }
         }
      
         long answer = Long.MAX_VALUE;
         for(int k = 0; k <= m; k++){
            answer = Math.min(answer,dp[n-1][k]);
         }
         out.println(answer);
      
      }
      
      
      
      out.close();
   }
   
      
}