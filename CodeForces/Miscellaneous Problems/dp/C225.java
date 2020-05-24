//make sure to make new file!
import java.io.*;
import java.util.*;

public class C225{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      char[][] matrix = new char[n][m];
      for(int k = 0; k < n; k++){
         matrix[k] = f.readLine().toCharArray();
      }
      
      int[] array = new int[m+1];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(matrix[k][j] == '#'){
               array[j+1]++;
            }
         }
      }
      
      
      int[][] dp = new int[m+1][2];         
      
      for(int k = 0; k <= m; k++){
         dp[k][0] = Integer.MAX_VALUE;
         dp[k][1] = Integer.MAX_VALUE;
      }
      
      dp[0][0] = 0;
      dp[0][1] = 0;
      
      for(int k = 0; k < m; k++){
         for(int j = 0; j < 2; j++){
            if(dp[k][j] == Integer.MAX_VALUE) 
               continue;
            
            if(j == 0){
               int sum = dp[k][j];
               for(int h = 1; h <= y && k+h <= m; h++){
                  sum += array[k+h];
                  if(h >= x){
                     dp[k+h][1] = Math.min(dp[k+h][1],sum);
                  }
               }
            }
            if(j == 1){
               int sum = dp[k][j];
               for(int h = 1; h <= y && k+h <= m; h++){
                  sum += n-array[k+h];
                  if(h >= x){
                     dp[k+h][0] = Math.min(dp[k+h][0],sum);
                  }
               }
            }
         }
         
      }
      
      out.println(Math.min(dp[m][0],dp[m][1]));
         
      
      
      
   
      
      
      
      
      
      out.close();
   }
   
      
}