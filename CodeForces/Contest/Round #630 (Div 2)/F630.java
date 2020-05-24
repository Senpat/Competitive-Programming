//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class F630{
   
   public static long MOD = 998244353;
   public static ArrayList<ArrayList<Integer>> adj;
   public static long[][][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
         
      }
      
      dp = new long[n+1][2][2];
   
      dfs(1,-1);
      
      long answer = (dp[1][1][0] + dp[1][0][0] + MOD)%MOD;
      out.println(answer);
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      if(adj.get(v).size() == 1 && p != -1){
      //if(p!=-1){
         dp[v][0][1] = 1L;
         dp[v][1][1] = 1L;
         dp[v][0][0] = 0L;
         dp[v][1][0] = 0L;
         return;
      }
      
      long sum0 = 0L;
      long sum1 = 0L;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         
         dp[v][0][1] = (dp[v][0][1] + dp[nei][1][1] + dp[nei][0][1] + dp[nei][0][0] + dp[nei][1][0] + MOD)%MOD;
         dp[v][1][1] = (dp[v][1][1]                 + dp[nei][0][1] + dp[nei][0][0] + dp[nei][1][0] + MOD)%MOD;
         
         dp[v][0][0] = (dp[v][0][0] + dp[nei][1][1] + dp[nei][0][1] + dp[nei][0][0] + dp[nei][1][0] + MOD)%MOD;
         dp[v][1][0] = (dp[v][1][0]                 + dp[nei][0][1] + dp[nei][0][0] + dp[nei][1][0] + MOD)%MOD;
         
         dp[v][1][0] = (dp[v][1][0] + dp[nei][0][1] * sum0 + MOD)%MOD;
         dp[v][0][0] = (dp[v][0][0] + dp[nei][0][1] * sum0 + MOD)%MOD;
         dp[v][0][0] = (dp[v][0][0] + dp[nei][0][1] * sum1 + MOD)%MOD;
         dp[v][0][0] = (dp[v][0][0] + dp[nei][1][1] * sum0 + MOD)%MOD;
         dp[v][0][0] = (dp[v][0][0] + dp[nei][1][1] * sum1 + MOD)%MOD;
         
         sum0 = (sum0 + dp[nei][0][1] + MOD)%MOD;
         sum1 = (sum1 + dp[nei][1][1] + MOD)%MOD;
      }
   }
         
         
      
}