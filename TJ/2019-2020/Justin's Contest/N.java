//make sure to make new file!
import java.io.*;
import java.util.*;

public class N{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] matrix = new int[n][m];
      for(int k = n-1; k >= 0; k--){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            matrix[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      
      int[][] dp = new int[n][m];
      
      dp[0][0] = matrix[0][0];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(k < n-1) dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j] + matrix[k+1][j]);
            if(j < m-1) dp[k][j+1] = Math.max(dp[k][j+1],dp[k][j] + matrix[k][j+1]);
         }
      }
      
      out.println(dp[n-1][m-1]);
      

      
      
      
      
      
      out.close();
   }
   
      
}