//Cactus
import java.io.*;
import java.util.*;

public class E231{
   
   public static long MOD = 1000000007L;
   
   public static int n,m,N;
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static boolean[] seen;
   public static boolean[] isbackedge;
   public static int[] backto;            //stores index of backedge that goes to v (if there is one)
   public static int[] backfrom;          //stores index of backedge that goes from v (if there is one)
   
   public static int[] cycleid;
   
   public static ArrayList<ArrayList<Edge>> adj2;
   public static int[] count;             //stores # of special nodes from there to root
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int P = 200005;
      long[] pow2 = new long[P];
      pow2[0] = 1L;
      for(int k = 1; k < P; k++){
         pow2[k] = (2L * pow2[k-1] + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      int[][] edges = new int[m][2];
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
         
         edges[k][0] = a;
         edges[k][1] = b;
      }
      
      //squash cycles using dfs tree
      
      backto = new int[n+1];
      backfrom = new int[n+1];
      Arrays.fill(backto,-1);
      Arrays.fill(backfrom,-1);
      
      seen = new boolean[n+1];
      isbackedge = new boolean[m];
      
      dfs(1,-1);
      
      cycleid = new int[n+1];
      squash(1,-1);
      
      //adj for squashed tree
      N = n+m+1;
      adj2 = new ArrayList<>(N);
      for(int k = 0; k < N; k++) adj2.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         int a = cycleid[edges[k][0]];
         int b = cycleid[edges[k][1]];
         if(a != b){
            adj2.get(a).add(new Edge(b,k));
            adj2.get(b).add(new Edge(a,k));
         }
      }
      
      count = new int[N];
      lca = new int[N][MAXD];
      depth = new int[N];
      depth[cycleid[1]] = 0;
      initdfs(cycleid[1],-1);
      initLCA();
      
      
      int q = Integer.parseInt(f.readLine());
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
      
         int a = cycleid[Integer.parseInt(st.nextToken())];
         int b = cycleid[Integer.parseInt(st.nextToken())];
         
         int lca = lca(a,b);
         
         int exp = count[a] + count[b] -2*count[lca];
         if(lca > n) exp++;
         
         long answer = pow2[exp];
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   //https://codeforces.com/blog/entry/68138
   public static void squash(int v, int p){
      //System.out.println(v);
      for(Edge e : adj.get(v)){
         if(e.to != p && !isbackedge[e.i]) squash(e.to,v);
      }
      
      if(backfrom[v] != -1){
         cycleid[v] = backfrom[v]+n+1;
      } else {
         cycleid[v] = v;
         for(Edge e : adj.get(v)){
            if(e.to == p || isbackedge[e.i]) continue;
            if(cycleid[e.to] != e.to && backto[e.to] == -1){
               cycleid[v] = cycleid[e.to];
            }
         
         }
      }
   }
   
   
   //dfs tree
   public static void dfs(int v, int p){
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]){
            backto[v] = e.i;
         } else {
            if(seen[e.to]){
               backfrom[v] = e.i;
               isbackedge[e.i] = true;
            } else {
               dfs(e.to,v);
            }
           
         }
      }
   }
   
   public static class Edge{
      int to;
      int i;
      public Edge(int a, int b){
         to = a;
         i = b;
      }
   }
   
   
   //lca and dist stuff
   public static int MAXD = 17;
   public static int[][] lca;
   
   public static int[] depth;
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 1; i < N; i++) {
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
      
      if(p != -1) count[v] = count[p];
      if(v > n) count[v]++;
      
      for(Edge e : adj2.get(v)){
         if(e.to == p) continue;
         depth[e.to] = depth[v]+1;
         lca[e.to][0] = v;
         initdfs(e.to,v);
      }
   }
      
}