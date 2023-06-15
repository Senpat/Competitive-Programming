//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2413{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      int N = 1000005;
      
      //0 -> split, 1 -> together
      long[][] dp = new long[N][2];
      dp[1][0] = 1L;
      dp[1][1] = 1L;
      
      for(int k = 1; k < N-1; k++){
         dp[k+1][1] = (dp[k][0] + 2*dp[k][1] + MOD)%MOD;
         dp[k+1][0] = (4L*dp[k][0] + dp[k][1] + MOD)%MOD;
      }
      
      
      for(int t = 0; t < q; t++){
         int x = Integer.parseInt(f.readLine());
         long answer = (dp[x][0] + dp[x][1] + MOD)%MOD;
         out.println(answer);
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}