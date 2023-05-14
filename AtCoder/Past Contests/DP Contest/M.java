//make sure to make new file!
import java.io.*;
import java.util.*;

public class M{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      long[][] dp = new long[n+1][m+1];          //dp[x][y] -> # of ways for first x children to use y candies
      dp[0][0] = 1L;
      for(int k = 1; k <= n; k++){
         long sum = 0;
         for(int j = 0; j <= m; j++){
            sum = (sum + dp[k-1][j] + MOD)%MOD;
            if(j-array[k]-1 >= 0) sum = ((sum-dp[k-1][j-array[k]-1])%MOD + MOD)%MOD;
            
            dp[k][j] = sum;
         }
      }
      
      out.println(dp[n][m]);
      
      
      
      
      
      
      out.close();
   }
   
      
}