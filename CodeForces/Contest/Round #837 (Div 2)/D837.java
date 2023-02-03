//make sure to make new file!
import java.io.*;
import java.util.*;

public class D837{
   
   public static int n;
   
   public static char[] chars;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int qq = 1; qq <= t; qq++){

         n = Integer.parseInt(f.readLine());
      
         chars = f.readLine().toCharArray();
         
         adj = new ArrayList<ArrayList<Integer>>(n);
         for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
      
         for(int k = 0; k < n-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         
         }
         
         lca = new int[n][MAXD];
         depth = new int[n];
         depth[0] = 0;
         initdfs(0,-1);
         initLCA();
      
         
         //make sure dp[k][j] == dp[j][k]
         dp = new int[n][n];
         for(int k = 0; k < n; k++) Arrays.fill(dp[k],-1);
         
         PriorityQueue<State> pq = new PriorityQueue<State>();
         //size 1
         for(int k = 0; k < n; k++){
            dp[k][k] = 1;
            pq.add(new State(k,k,0));
         }
         
         //size 2
         for(int k = 0; k < n; k++){
            for(int j = k+1; j < n; j++){
               if(chars[k] == chars[j]){
                  dp[k][j] = 2;
                  dp[j][k] = 2;
                  pq.add(new State(k,j,dist(k,j)));
               }  
            }
         }
         
         while(!pq.isEmpty()){
            State s = pq.poll();
            int a = s.a;
            int b = s.b;
            
            int curdist = s.len;
            
            int ap = -1;
            int bp = -1;
            for(int nei : adj.get(a)){
               if(dist(nei,b) < curdist){
                  ap = nei;
               } else {
                  if(dp[nei][b] == -1) pq.add(new State(nei,b,curdist+1));
                  if(dp[a][b] > dp[nei][b]){
                     dp[nei][b] = dp[a][b];
                     dp[b][nei] = dp[a][b];
                  }
               }
            }
            
            for(int nei : adj.get(b)){
               if(dist(nei,a) < curdist){
                  bp = nei;
               } else {
                  if(dp[a][nei] == -1) pq.add(new State(a,nei,curdist+1));
                  if(dp[a][b] > dp[a][nei]){
                     dp[a][nei] = dp[a][b];
                     dp[nei][a] = dp[a][b];
                  }
               }
            }
            
            for(int na : adj.get(a)){
               for(int nb : adj.get(b)){
                  if(ap == na || bp == nb || na == nb) continue;
                  
                  if(chars[na] == chars[nb]){
                     if(dp[na][nb] == -1) pq.add(new State(na,nb,curdist+2));
                     if(dp[a][b] + 2 > dp[na][nb]){
                        dp[na][nb] = dp[a][b]+2;
                        dp[nb][na] = dp[a][b]+2;
                     }
                     
                  }
               }
            }
         }
         
         int answer = 0;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               answer = Math.max(answer,dp[k][j]);
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int a;
      int b;
      int len;
      public State(int c, int d, int l){
         a = c;
         b = d;
         len = l;
      }
      
      public int compareTo(State s){
         return len - s.len;
      }
   }
   
   
   //lca and dist stuff
   public static int MAXD = 13;
   public static int[][] lca;
   public static int[] depth;
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 0; i < n; i++) {
            lca[i][d] = lca[lca[i][d-1]][d-1];
         }
      }
   }
   
   public static int lca(int a, int b){
      
      if(depth[a] < depth[b]){
         //swap a and b
         int temp = a;
         a = b;
         b = temp;
      }
      
      for(int i = MAXD-1; i >= 0; i--){
         if (((depth[a]-depth[b]) & (1<<i)) != 0){
         //if(depth[a] - (1<<i) > depth[b]){
            a = lca[a][i];
         }
      }
      if(a==b) return a;
      
      for(int i = MAXD-1; i >= 0; i--){
         if(lca[a][i] != lca[b][i]){
            a = lca[a][i];
            b = lca[b][i];
         }
      }
      return lca[a][0];
   }
            
   
   
   public static int dist(int a, int b){
      //System.out.println("lca: " + lca(a,b));
      return depth[a] + depth[b] - 2*depth[lca(a,b)];
   }
   
   public static void initdfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         depth[nei] = depth[v]+1;
         lca[nei][0] = v;
         initdfs(nei,v);
      }
   }
      
      
}