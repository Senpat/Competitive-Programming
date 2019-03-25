//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial but tle
public class D1114{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][][] dp = new int[n][n][2];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            for(int h = 0; h < 2; h++){
               if(k==j){
                  dp[k][j][h] = 0;
               } else {
                  dp[k][j][h] = Integer.MAX_VALUE>>1;
               }
            }
         }
      }
      
      
      for(int r = 0; r < n; r++){
         for(int l = r; l >= 0; l--){
            for(int it = 0; it < 2; it++){
               int c = array[l];
               if(it == 1) c = array[r];
               
               if(l > 0){
                  dp[l-1][r][0] = Math.min(dp[l-1][r][0], dp[l][r][it] + (c == array[l-1] ? 0 : 1));
               }
               
               if(r < n-1){
                  dp[l][r+1][1] = Math.min(dp[l][r+1][1], dp[l][r][it] + (c == array[r+1] ? 0 : 1));
               }
            }
         }
      }
      
      out.println(Math.min(dp[0][n-1][0],dp[0][n-1][1]));
      
      

      
      
      
      
      out.close();
   }
   
      
}