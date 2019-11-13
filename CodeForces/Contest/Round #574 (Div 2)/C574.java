//make sure to make new file!
import java.io.*;
import java.util.*;

public class C574{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[][] array = new long[n][2];
      for(int k = 0; k < n; k++) array[k][0] = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      long[] b = new long[n];
      for(int k = 0; k < n; k++) array[k][1] = Long.parseLong(st.nextToken());
      
      
      long[][] dp = new long[n][2];
      
      dp[0][0] = array[0][0];
      dp[0][1] = array[0][1];
      
      long max = 0;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < 2; j++){
            if(k < n-1) dp[k+1][1-j] = Math.max(dp[k+1][1-j],dp[k][j] + array[k+1][1-j]);
            if(k < n-2) dp[k+2][1-j] = Math.max(dp[k+2][1-j],dp[k][j] + array[k+2][1-j]);
            max = Math.max(max,dp[k][j]);
         }
      }
      
      out.println(max);
      
      
      
      
      out.close();
   }
   
      
}