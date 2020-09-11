//make sure to make new file!
import java.io.*;
import java.util.*;

public class TemplateCf{
   
   public static long r1;
   public static long r2;
   public static long r3;
   public static long d;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long r1 = Long.parseLong(st.nextToken());
      long r2 = Long.parseLong(st.nextToken());
      long r3 = Long.parseLong(st.nextToken());
      long d = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLOng(st.nextToken());
         
      }
      
      long[][] dp = new long[n][3];
      for(int k = 0; k < n; k++){
         dp[k][0] = Long.MAX_VALUE;
         dp[k][1] = Long.MAX_VALUE;
         dp[k][2] = Long.MAX_VALUE;
      }
      
      //initial
      dp[0][0] = nogoback(array[0]);
      dp[0][1] = goback(array[0]) + 2L*d;
      
      dp[0][2] = goback(array[0]) + ((long)n-1L)*d;
      
      for(int k = 1; k < n; k++){
         long ngb = nogoback(array[k]);
         long gb = goback(array[k]);
         dp[k][0] = Math.min(Math.min(dp[k-1][0],dp[k-1][1])+ngb,dp[k-1][1] + gb);
         dp[k][1] = gb + 2L*d;
         dp[k][2] = dp[k-1][2] + Math.min(ngb,gb);
         dp[k][2] = Math.min(dp[k][2], dp[k-1][0] + gb + ((long)(n-1-k))*d);
      }
      
      long answer = Math.min(dp[n-1][0],Math.min(dp[n-1][1],dp[n-1][2]));
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long nogoback(long x){
      return Math.min(r1,r3)*x + r3;
   }
   
   public static long goback(long x){
      return Math.min(r2+Math.min(r1,r3), Math.min(r1,r3)*x + r1*2L);
   }
   
      
}