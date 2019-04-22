//Xenia and Weights
import java.io.*;
import java.util.*;
//centroid decomposition, lca, and dist
//https://medium.com/carpanese/an-illustrated-introduction-to-centroid-decomposition-8c1989d53308
public class E342{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int n;
   public static int[] ans;
   public static int INF = 2146483647;        //Integer.MAX_VALUE - 1000000
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      cadj = new ArrayList<HashSet<Integer>>(n+1);
      for(int k = 0; k <= n; k++) cadj.add(new HashSet<Integer>());
      
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         a--;
         b--;
      
         adj.get(a).add(b);
         adj.get(b).add(a);
         
         cadj.get(a).add(b);
         cadj.get(b).add(a);
      }
      
      lca = new int[n+1][MAXD];
      depth = new int[n+1];
      depth[0] = 0;
      initdfs(0,-1);
      initLCA();
      
      //for(int k = 0; k < n; k++) out.print(depth[k] + " ");
      
      ans = new int[n];
      Arrays.fill(ans,INF);
      ans[0] = 0;
      
      par = new int[n];
      sub = new int[n];
      
      build(0,-1);
      
      //for(int k = 0; k < n; k++) out.println(par[k] + " " + sub[k]);
      update(0);
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int q = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         x--;
         if(q == 1){
            //update
            update(x);
         
         } else if(q == 2){
            //query
            int answer = query(x);
            out.println(answer);
         
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static void update(int x){
      int i = x;
      while(par[i] != i){
         ans[i] = Math.min(ans[i],dist(i,x));
         i = par[i];
      }
      ans[i] = Math.min(ans[i],dist(i,x));
   
   }
   
   public static int query(int x){
      int min = Integer.MAX_VALUE;
      int i = x;
      while(par[i] != i){
         min = Math.min(min,dist(i,x) + ans[i]);
         i = par[i];
      }
      min = Math.min(min,dist(i,x) + ans[i]);
      return min;
   }
   
   //centroid decomposition stuff
   public static ArrayList<HashSet<Integer>> cadj;
   public static int[] par;
   public static int[] sub;
   
   public static void build(int v, int p){
      int s = dfs1(v,p);
      int centroid = dfs2(v,p,s);
      //System.out.println(" " + centroid + " " + p);
      if(p == -1){
         p = centroid;
      }
      
      par[centroid] = p;
      
      ArrayList<Pair> pairs = new ArrayList<Pair>();
      for(int nei : cadj.get(centroid)){
         pairs.add(new Pair(nei,centroid));
      }
      
      for(Pair pr : pairs){
         cadj.get(pr.a).remove(pr.b);
         cadj.get(pr.b).remove(pr.a);
         build(pr.a,pr.b);
      }
      /*
      for(int nei : cadj.get(centroid)){
         cadj.get(nei).remove(centroid);
         cadj.get(centroid).remove(nei);
         build(nei,centroid);
      }*/
         
         
   
   }
   
   //find centroid
   public static int dfs2(int v, int p, int s){
      for(int nei : cadj.get(v)){
         if(nei != p && sub[nei] > s/2){
            return dfs2(nei,v,s);
         }
      }
      return v;
   }
   
   //find size of subtree for every node
   public static int dfs1(int v, int p){
      sub[v] = 1;
      
      for(int nei : cadj.get(v)){
         if(nei != p){
            sub[v] += dfs1(nei,v);
         }
      }
      
      return sub[v];
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
   }
   
   
   
   //lca and dist stuff
   public static int MAXD = 17;
   public static int[][] lca;
   public static int[] depth;
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 0; i < n+1; i++) {
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