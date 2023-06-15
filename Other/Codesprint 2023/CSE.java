//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSE{

   public static long[] pow10;
   public static long[] x;
   public static long[][][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long D = 10;
      pow10 = new long[D+1];
      pow10[0] = 1L;
      for(int k = 1; k <= D; k++){
         pow10[k] = (10L * pow10[k-1]);
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int q = Integer.parseInt(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      
      long[] queries = new long[q];
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         st.nextToken();
         queries[k] = Long.parseLong(st.nextToken());
      }
      
      long[] c = new long[10];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < 10; k++){
         c[k] = Long.parseLong(st.nextToken());
      }
      
      long answer = 0L;
      for(int t = 0; t < q; t++){
         long X = queries[t];
         long[] x = new long[D+1];
         int d = 0;
         while(X > 0){
            x[d] = x%10;
            x /= 10;
         }
         
         dp = new long[D][10][2];
         for(int k = 0; k < D; k++){
            for(int j = 0; j < 10; j++){
               dp[k][j][0] = Integer.MAX_VALUE;
               dp[k][j][1] = Integer.MAX_VALUE;
            }
         }
         
         dothing(D,0,1);
         answer += dp[D][0][1];
         
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   
   public static void dothing(int digit, int available, int ismax){
      if(dp[digit][available][ismax] != Integer.MAX_VALUE) return;
      
      long ret = Integer.MAX_VALUE;
      
      
      
      
      dp[digit][available][ismax] = ret;
   }
   
      
}