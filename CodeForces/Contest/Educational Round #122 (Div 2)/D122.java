//make sure to make new file!
import java.io.*;
import java.util.*;

public class D122{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000;
      
      int MAXM = 20000;
      
      int[] precomp = new int[N+1];
      Arrays.fill(precomp,Integer.MAX_VALUE);
      precomp[1] = 0;
      for(int k = 1; k <= N; k++){
         for(int j = 1; j <= k; j++){
            if(k+k/j <= N) precomp[k+k/j] = Math.min(precomp[k+k/j],precomp[k]+1);
         }
         
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         int[] b = new int[n];
         int[] num = new int[n];
         long[] c = new long[n];
         long all = 0;
         for(int k = 0;  k< n; k++){
            b[k] = Integer.parseInt(st1.nextToken());
            c[k] = Long.parseLong(st2.nextToken());
            
            num[k] = precomp[b[k]];
            if(num[k] != Integer.MAX_VALUE){
               all+=c[k];
            }
         }
         
         if(m >= MAXM){
            out.println(all);
            continue;
         }
         
         long[][] dp = new long[n][m+1];
         for(int k = 0; k < n; k++) Arrays.fill(dp[k],-1);
         dp[0][0] = 0;
         if(num[0] <= m) dp[0][num[0]] = c[0];
         
         for(int k = 0; k < n-1; k++){
            for(int j = 0; j <= m; j++){
               if(dp[k][j] == -1) continue;
               
               //don't use
               dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j]);
               
               //use
               if(num[k+1] != Integer.MAX_VALUE && j+num[k+1] <= m) dp[k+1][j+num[k+1]] = Math.max(dp[k+1][j+num[k+1]],dp[k][j]+c[k+1]);
            }
         }
         
         long max = 0;
         for(int k = 0; k <= m; k++){
            max = Math.max(dp[n-1][k],max);
         }
         out.println(max);
         
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}