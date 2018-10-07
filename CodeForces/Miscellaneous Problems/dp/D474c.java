//Flowers
//self, without biginteger
import java.io.*;
import java.util.*;
import java.math.*;

public class D474c{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] ql = new int[n];
      int[] qh = new int[n];
      
      int maxq = 0;
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         ql[k] = Integer.parseInt(st.nextToken());
         qh[k] = Integer.parseInt(st.nextToken());
         maxq = Math.max(maxq, qh[k]);
      }
      
      long[] dp = new long[100001+m];
      
      dp[1] = 1L;
      
      dp[m] += 1;
      
      for(int k = 0; k < 100001; k++){
         dp[k] %= 1000000007;
         dp[k+1] += dp[k];
         dp[k+m] += dp[k];
         
      }
      
      //prefix sums
      
      long[] ps = new long[dp.length];
      
      ps[0] = 0L;
      for(int k = 1; k < dp.length; k++){
         ps[k] = (ps[k-1] + dp[k])%1000000007;
         
      }
      
      for(int k = 0; k < n; k++){
         long i = (ps[qh[k]]- ps[ql[k]-1])%1000000007;
         if(i<0) i+=1000000007;
         out.println(i);
      }
      
      
      
      out.close();
   }
   
}