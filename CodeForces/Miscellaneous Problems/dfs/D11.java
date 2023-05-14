//A Simple Task
import java.io.*;
import java.util.*;
//counting cycles practice
public class D11{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int np2 = (1 << n);
      
      boolean[][] adj = new boolean[n][n];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
         
         adj[a][b] = true;
         adj[b][a] = true;
      }
      
      boolean[] enough = new boolean[np2];
      for(int k = 0; k < np2; k++){
         enough[k] = num1(k) > 2;
      }
      
      long answer = 0;
      for(int k = n-1; k >= 0; k--){
         //start at k
         long[][] dp = new long[k+1][(1 << (k+1))];        //dp[a][b][mask] -> # of ways to go from a to b using the nodes in mask
         dp[k][(1 << k)] = 1;
         for(int mask = 0; mask < (1 << (k+1)); mask++){
            for(int to = 0; to <= k; to++){
               if(dp[to][mask] == 0) continue;
               for(int nei = 0; nei < k; nei++){
                  if(adj[to][nei] && (mask & (1 << nei)) == 0){
                     dp[nei][mask ^ (1 << nei)] += dp[to][mask];
                  }
               }
               //finish cycle, nei == k
               if(adj[to][k] && enough[mask]){
                  answer += dp[to][mask];
               }
            }
         }
      }
      
      answer/=2;
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   public static int num1(int x){
      int ret = 0;
      while(x > 0){
         if((x&1) == 1){
            ret++;
         }
         x >>= 1;
      }
      return ret;
   }
      
}