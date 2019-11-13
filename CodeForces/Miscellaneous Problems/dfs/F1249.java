//Maximum Weight Subset
import java.io.*;
import java.util.*;

public class F1249{
   
   public static int[] array;
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[][] dists;
   public static ArrayList<ArrayList<Integer>> maway;
   public static int[] dp;
   public static HashSet<Integer> seen;
   public static HashSet<Integer> counted;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      array = new int[n+1];
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      //floyd warshall
      dists = new int[n+1][n+1];
      for(int k = 0; k <= n; k++){
         Arrays.fill(dists[k],Integer.MAX_VALUE);
         dists[k][k] = 0;
      }
      
      for(int k = 1; k <= n; k++){
         for(int i : adj.get(k)){
            dists[k][i] = 1;
         }
      }
      
      for(int k = 1; k <= n; k++){
         for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
               
               if(dists[i][k] < Integer.MAX_VALUE && dists[k][j] < Integer.MAX_VALUE && dists[i][k] + dists[k][j] < dists[i][j]){
                  dists[i][j] = dists[i][k] + dists[k][j];
               }
            }
         }
      }
      
      //maway est le array que garder tous les vertices m+1 distance de le vertex
      maway = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) maway.add(new ArrayList<Integer>(n+1));
      
      for(int k = 1; k <= n; k++){
         for(int j = k+1; j <= n; j++){
            if(dists[k][j] == m+1){
               maway.get(k).add(j);
               maway.get(j).add(k);
            }
         }
      }
      
      
      dp = new int[n+1];
      seen = new HashSet<Integer>();
      counted = new HashSet<Integer>();
      dfs(1,-1);
      
      
      int answer = 0;
      for(int k = 1; k <= n; k++){
         answer = Math.max(dp[k],answer);
      }
      
      out.println(answer);
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      if(p == -1){
         dp[v] = array[v];
      } else {
         //utilise pas, dp[p]
         //utilise
         int sum = 0;
         for(int i : maway.get(v)){
            if(seen.contains(i) && !counted.contains(i)){
               sum += dp[i];
               counted.add(i);
            }
         }
         if(sum == 0) dp[v] = array[v];
         else dp[v] = Math.max(dp[p],sum + array[v]);
      }
      
      seen.add(v);
      
      for(int nei : adj.get(v)){
         if(seen.contains(nei)) continue;
         dfs(nei,v);
      }
   }
      
      
      
      
}