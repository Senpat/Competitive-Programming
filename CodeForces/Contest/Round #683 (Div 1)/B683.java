//make sure to make new file!
import java.io.*;
import java.util.*;

public class B683{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      
      int[][] dp = new int[n+1][m+1];
      
      for(int k = 0; k <= n; k++) Arrays.fill(dp[k],0);
      
      for(int k = 0; k <= n; k++){
         for(int j = 0; j <= m; j++){
            if(k == 0 || j == 0){
               dp[k][j] = 0;
            } else if(a[k-1] == b[j-1]){
               dp[k][j] = Math.max(dp[k][j],dp[k-1][j-1]+2);
            } else {
               dp[k][j] = Math.max(dp[k][j],Math.max(dp[k-1][j]-1,dp[k][j-1]-1));
            }
         }
      }
      
      int max = 0;
      for(int k = 0; k <= n; k++){
         for(int j = 0; j <= m; j++){
            max = Math.max(max,dp[k][j]);
         }
      }
      
      out.println(max);
         
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}