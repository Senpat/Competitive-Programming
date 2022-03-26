//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static long MOD = 998244353L;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[][] dp;
   
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
      
      //1 is where edge to parent is part of a group, 0 is where it's not
      dp = new long[n+1][2];
      
      dfs(1,-1);
      
      long answer = dp[1][0];
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int p){
      //calculate dp[v][0] and dp[v][1]
      
      int numchildren = 0;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         numchildren++;
      }
      
      if(numchildren == 0){
         dp[v][0] = 1;
         dp[v][1] = 0;
         return;
      }
      
      long[][] cur = new long[2][2];
      long[][] next = new long[2][2];
      
      cur[0][0] = -1;
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         
         long x1 = dp[nei][1];
         long x2 = dp[nei][0];               //need an even # of these
         
         if(cur[0][0] == -1){
            //first one
            
            cur[0][0] = x1;
            cur[0][1] = x2;
            cur[1][0] = x2;
            cur[1][1] = 0;
         } else {
            
            next[0][0] = ((cur[0][0]*x1 + MOD)%MOD + (cur[1][0]*x2 + MOD)%MOD + MOD)%MOD;
            next[1][0] = ((cur[0][0]*x2 + MOD)%MOD + (cur[1][0]*x1 + MOD)%MOD + MOD)%MOD;
            next[0][1] = ((cur[0][0]*x2 + MOD)%MOD + (cur[0][1]*x1 + MOD)%MOD + (cur[1][1]*x2 + MOD)%MOD + MOD)%MOD;
            next[1][1] = ((cur[1][0]*x2 + MOD)%MOD + (cur[0][1]*x2 + MOD)%MOD + (cur[1][1]*x1 + MOD)%MOD + MOD)%MOD;
            
            cur[0][0] = next[0][0];
            cur[0][1] = next[0][1];
            cur[1][0] = next[1][0];
            cur[1][1] = next[1][1];
         }
      }
      
      dp[v][0] = cur[0][0];
      dp[v][1] = cur[0][1];
      
   }
}