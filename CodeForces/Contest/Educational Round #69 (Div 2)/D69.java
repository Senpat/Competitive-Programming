//make sure to make new file!
import java.io.*;
import java.util.*;

public class D69{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[][] dp = new long[n][m];
      for(int k = 0; k < m; k++) Arrays.fill(dp[n],Long.MIN_VALUE);
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         dp[k][0] = Math.max(dp[k][0],array[k]);
         for(int j = 0; j < m; j++){
            
      

      
      
      
      
      out.close();
   }
   
      
}