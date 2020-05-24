//make sure to make new file!
import java.io.*;
import java.util.*;

public class L{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      boolean[][] dp = new boolean[n][m+1];
      
      dp[0][0] = true;
      if(array[0] <= m) dp[0][array[0]] = true;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= m; j++){
            if(!dp[k][j]) continue;
            
            dp[k+1][j] = true;
            if(j + array[k+1] <= m){
               dp[k+1][j+array[k+1]] = true;
            }
         }
      }
      
      for(int k = m; k >= 0; k--){
         if(dp[n-1][k]){
            out.println(k);
            out.close();
            return;
         }
      }
      

      
      
      
      
      
      out.close();
   }
   
      
}