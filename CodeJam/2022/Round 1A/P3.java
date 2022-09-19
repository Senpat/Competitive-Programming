//make sure to make new file!
import java.io.*;
import java.util.*;
//in-contest fail
public class P3{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         int[][] input = new int[n][w];
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            
            for(int j = 0; j < w; j++){
               input[k][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         //precompute mins
         int[][][] mins = new int[n][n][w];        //mins[l][r][w] = min number of w in the range l to r
         for(int k = 0; k < n; k++){
            for(int j = 0; j < w; j++){
               mins[k][k][j] = input[k][j];
            }
         }
         
         
         for(int j = 2; j <= n; j++){
            for(int k = 0; k+j-1 < n; k++){
               for(int h = 0; h < w; h++){
                  mins[k][k+j-1][h] = Math.min(mins[k][k+j-2][h],mins[k+j-1][k+j-1][h]);
               }
            }
         }
         
         
         int[][] dp = new int[n][n];
         for(int k = 0; k < n; k++) Arrays.fill(dp[k],Integer.MAX_VALUE);
         
         for(int k = 0; k < n; k++){
            dp[k][k] = 0;
         }
         
         for(int len = 2; len <= n; len++){
            for(int l = 0; l+len-1 < n; l++){
               int r = l + len - 1;
               
               //split
               int min = Integer.MAX_VALUE;
               for(int s = l; s < r; s++){
                  int cur = dp[l][s] + dp[s+1][r];
                  //plus the cost to replace
                  for(int h = 0; h < w; h++){
                     cur += Math.abs(mins[l][s][h]-mins[s+1][r][h]);
                  }
                  min = Math.min(min,cur);
               }
               
               dp[l][r] = min;
            }
         }
         
         int answer = dp[0][n-1];
         for(int k = 0; k < w; k++){
            answer += input[0][k];
            if(n > 1){
               answer += input[n-1][k];
            }
         }
         
         out.println("Case #" + q + ": " + answer);
               
               
               
         
         
         
         
               
      
      }
      
      
      
      
      out.close();
   }
   
      
}