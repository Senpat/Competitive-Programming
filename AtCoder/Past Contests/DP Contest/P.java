//make sure to make new file!
import java.io.*;
import java.util.*;

public class P{
   
   public static long MOD = 1000000007L;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[][] dp;          //0 -> if that node is not used, 1 -> is that node is used
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      dp = new long[n+1][2];
      
      dfs(1,-1);
      
      long answer = (dp[1][0] + dp[1][1] + MOD)%MOD;
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      dp[v][0] = 1L;
      dp[v][1] = 1L;
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         
         long sum = (dp[nei][0] + dp[nei][1] + MOD)%MOD;
         dp[v][0] = (dp[v][0] * sum + MOD)%MOD;
         dp[v][1] = (dp[v][1] * dp[nei][0] + MOD)%MOD;
      }
      
   }
      
}