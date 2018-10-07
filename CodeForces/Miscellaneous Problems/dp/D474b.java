//Flowers
//self
import java.io.*;
import java.util.*;
import java.math.*;

public class D474b{
   
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
      
      BigInteger[] dp = new BigInteger[100001+m];
      Arrays.fill(dp,BigInteger.ZERO);
      
      
      dp[1] = BigInteger.ONE;
      
      dp[m] = dp[m].add(BigInteger.valueOf(1));
      
      for(int k = 0; k <= maxq; k++){
         dp[k+1] = dp[k+1].add(dp[k]);
         dp[k+m] = dp[k+m].add(dp[k]);
      }
      
      //prefix sums
      
      BigInteger[] ps = new BigInteger[dp.length];
      
      ps[0] = BigInteger.valueOf(0);
      for(int k = 1; k < dp.length; k++){
         ps[k] = ps[k-1].add(dp[k]);
      }
      
      for(int k = 0; k < n; k++){
         out.println((ps[qh[k]].subtract(ps[ql[k]-1])).remainder(BigInteger.valueOf(1000000007)));
      }
      
      
      
      out.close();
   }
   
}