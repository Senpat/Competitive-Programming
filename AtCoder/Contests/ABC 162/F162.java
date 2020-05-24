//make sure to make new file!
import java.io.*;
import java.util.*;

public class F162{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      if(n%2 == 0){
         long[][] dp = new long[n][2];
         
         for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MIN_VALUE);
         dp[0][0] = array[0];
         dp[1][1] = array[1];
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < 2; j++){
               if(dp[k][j] == Long.MIN_VALUE) continue;
               
               //no skip
               if(k+2 < n){
                  dp[k+2][j] = Math.max(dp[k+2][j],dp[k][j] + array[k+2]);
               }
               //1 skip
               if(k+3 < n && j < 1){
                  dp[k+3][j+1] = Math.max(dp[k+3][j+1],dp[k][j] + array[k+3]);
               }
            }
         }
         
         long answer = Math.max(dp[n-2][0],dp[n-1][1]);
         
         out.println(answer);
      
      
      } else {
         
         long[][] dp = new long[n][3];
         
         for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MIN_VALUE);
         dp[0][0] = array[0];
         dp[1][1] = array[1];
         dp[2][2] = array[2];
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < 3; j++){
               if(dp[k][j] == Long.MIN_VALUE) continue;
               
               //no skip
               if(k+2 < n){
                  dp[k+2][j] = Math.max(dp[k+2][j],dp[k][j] + array[k+2]);
               }
               //1 skip
               if(k+3 < n && j < 2){
                  dp[k+3][j+1] = Math.max(dp[k+3][j+1],dp[k][j] + array[k+3]);
               }
               //2 skip
               if(k+4 < n && j < 1){
                  dp[k+4][j+2] = Math.max(dp[k+4][j+2],dp[k][j] + array[k+4]);
               }
            }
         }
         
         long answer = Math.max(dp[n-3][0],dp[n-2][1]);
         answer = Math.max(answer,dp[n-1][2]);
         
         out.println(answer);
      
      }
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}