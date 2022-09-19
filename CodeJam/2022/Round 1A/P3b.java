//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class P3b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         int[][] weights = new int[n][w];
         int[] sumweights = new int[n];
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < w; j++){
               weights[k][j] = Integer.parseInt(st.nextToken());
               sumweights[k] += weights[k][j];
            }
         }
         
         int[][][] maxsame = new int[w][n][n];           //max of weight w that's in from l to r
         
         for(int k = 0; k < w; k++){
            for(int l = 0; l < n; l++){
               maxsame[k][l][l] = weights[l][k];
               for(int r = l+1; r < n; r++){
                  maxsame[k][l][r] = Math.min(maxsame[k][l][r-1],weights[r][k]);
               }
            }
         }
         
         int[][] maxsum = new int[n][n];
         for(int l = 0; l < n; l++){
            for(int r = l+1; r < n; r++){
               for(int k = 0; k < w; k++){
                  maxsum[l][r] += maxsame[k][l][r];
               }
            }
         }
         
         int[][] dp = new int[n][n];
         for(int k = 0; k < n; k++){
            Arrays.fill(dp[k],Integer.MAX_VALUE);
            dp[k][k] = 0;
         }
         
         int l,r;
         for(int d = 1; d < n; d++){
            for(l = 0; l < n && (r=(l+d)) < n; l++){
               
               int min = Integer.MAX_VALUE;
               for(int s = l; s < r; s++){
                  int cur = dp[l][s] + dp[s+1][r] + sumweights[s]-maxsum[l][r] + sumweights[s+1]-maxsum[l][r];
                  min = Math.min(min,cur);
               }
               
               dp[l][r] = min;
            }
         }
         
         int answer = dp[0][n-1] + sumweights[0] + sumweights[n-1];
         out.println("Case #" + q + ": " + answer);
               

      }
      
      
      
      
      out.close();
   }
   
      
}