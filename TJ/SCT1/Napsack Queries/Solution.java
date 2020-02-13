//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{
   
   public static long MOD = 1000000007;
   public static int N = 200005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int q = Integer.parseInt(st.nextToken());
      int p = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[p];
      
      for(int k = 0; k < p; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      long[] dp = new long[N];
      
      dp[0] = 1;
      for(int k = 1; k < N; k++){
         for(int j = 0; j < p; j++){
            if(k-array[j] >= 0){
               dp[k] = (dp[k] + dp[k-array[j]] + MOD) % MOD;
            }
         }
      }
      
      long[] psums = new long[N+1];
      psums[0] = 0;
      for(int k = 0; k < N; k++){
         psums[k+1] = (dp[k] + psums[k] + MOD)%MOD;
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         
         long answer = psums[n+d+1]-psums[n-d];
         while(answer < 0) answer += MOD;
         
         out.println(answer);
         
      }
      
      
      
      out.close();
   }
   
      
}