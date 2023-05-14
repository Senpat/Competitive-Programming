//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());      
     
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
      }
      
      dp = new int[n+1];
      Arrays.fill(dp,-1);
      
      for(int k = 1; k <= n; k++){
         if(dp[k] == -1){
            dfs(k);
         }
      }
      
      int max = 0;
      for(int k = 1; k <= n; k++){
         max = Math.max(max,dp[k]);
      }
      out.println(max);
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      if(dp[v] != -1) return;
      
      int max = 0;
      for(int nei : adj.get(v)){
         dfs(nei);
         max = Math.max(max,dp[nei]+1);
      }
      
      dp[v] = max;
   }
   
      
}