//Sonya and Problem Wihtout a Legend
import java.io.*;
import java.util.*;
//tutorial/comments
public class C713{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      long[] sort = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken())-k;
         sort[k] = array[k];
      }
      
      Arrays.sort(sort);
      
      long[][] dp = new long[n][n];
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Long.MAX_VALUE);
      
      dp[0][0] = Math.abs(array[0]-sort[0]);
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(k < n-1){
               dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j] + Math.abs(array[k+1]-sort[j]));
            }
            if(j < n-1){
               if(k == 0) dp[k][j+1] = Math.min(dp[k][j+1],Math.abs(array[k]-sort[j+1]));
               dp[k][j+1] = Math.min(dp[k][j+1],dp[k][j]);
            }
         }
      }
      
      out.println(dp[n-1][n-1]);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}