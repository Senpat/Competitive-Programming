//Minimum spanning tree for each edge
import java.io.*;
import java.util.*;
//WA tc19
public class E609{

   public static ArrayList<ArrayList<Edge>> adj;
   public static int[] parent;
   public static int[] sizes;
   public static Edge[] edges;
   
   public static int MAXN = 200005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      edges = new Edge[m];
      
      //find minimum spanning tree
      
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
      adj = new ArrayList<ArrayList<Edge>>(n);
      for(int k = 0; k < n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long w = Long.parseLong(st.nextToken());
         
         a--;
         b--;
         
         pq.add(new Edge(a,b,w));
         
         edges[k] = new Edge(a,b,w);
      }
      
      long sum = 0L;
      
      parent = new int[n];
      sizes = new int[n];
      
      for(int k = 0; k < n; k++) parent[k] = k;
      Arrays.fill(sizes,1);
           
      ArrayList<Edge> usededges = new ArrayList<Edge>();
      
      while(!pq.isEmpty()){
         Edge e = pq.poll();
         if(find(e.a) != find(e.b)){
            union(e.a,e.b);
            adj.get(e.a).add(new Edge(e.a,e.b,e.w));
            adj.get(e.b).add(new Edge(e.b,e.a,e.w));
            usededges.add(e);
            sum += e.w;
         }
      }
      
      
      
      
      //instantiate segtree and hld
      segtree = new long[4*MAXN];
      lca = new int[MAXN][MAXD];
      depth = new int[MAXN];
      
      subsize = new int[MAXN];
      vertextosegtree = new int[MAXN];
      topchain = new int[MAXN];
      vals = new long[MAXN];
      
      //make vals;
      dfssize(0,-1);
      for(Edge e : usededges){
         if(depth[e.a] > depth[e.b]){
            vals[e.a] = e.w;
         } else {
            vals[e.b] = e.w;
         }
      }
      /*
      for(int k = 0; k < n; k++){
         out.println(vals[k]);
      }*/
      
      inithld();
      
      for(int k = 0; k < m; k++){
         long answer = sum + edges[k].w - query(edges[k].a,edges[k].b);
         //out.println(answer + " " + edges[k].w + " " + query(edges[k].a,edges[k].b));
         out.println(answer);
      }
      
      
      
      out.close();
   }
   
   
   
   //SEGTREE, HLD, AND LCA FUNCTIONS
   
   //dont forget 0 indexed edges
   public static long[] segtree;
   public final static int MAXD = 22;
   public static int[][] lca;
   public static int[] depth;
   
   public static void stupdate(int index, int l, int r, int i, long x){
      if(l==r) segtree[index] = x;
      else {
         int mid = (l+r)/2;
         if(i <= mid) stupdate(2*index, l, mid, i, x);
         else stupdate(2*index+1, mid+1, r, i, x);
         segtree[index] = Math.max(segtree[index*2],segtree[index*2 + 1]);                   //max edge query
      }
   }
   
   public static void stupdate(int i, long x){
      stupdate(1,0,MAXN-1,i,x);
   }
   
   public static long stquery(int index, int l, int r, int lhs, int rhs){
      if(l >= lhs && r <= rhs) 
         return segtree[index];
      long ret = 0;
      int mid = (l+r)/2;
      if(mid >= lhs) ret = Math.max(ret,stquery(2*index, l, mid, lhs, rhs));
      if(mid+1 <= rhs) ret = Math.max(ret, stquery(2*index+1, mid+1, r, lhs,rhs));
      return ret;
   }
   
   public static long stquery(int l, int r){
      return stquery(1,0,MAXN,l,r);
   }
   
   public static int getlca(int a, int b){
   
      if(depth[a] < depth[b]){
         //swap a and b;
         int temp = b;
         b = a;
         a = temp;
      }
      
      for(int d = MAXD-1; d >= 0; d--){
         if(depth[a] - (1<<d) >= depth[b]){
            a = lca[a][d];
         }
      }
      
      for(int d = MAXD-1; d >= 0; d--){
         if(lca[a][d] != lca[b][d]){
            a = lca[a][d];
            b = lca[b][d];
         }
      }
      
      if( a != b){
         a = lca[a][0];
         b = lca[b][0];
      }
      
      return a;
   }
   
   public static void initlca(){
      for(int d = 1; d < MAXD; d++){
         for(int k = 0; k < MAXN; k++){
            lca[k][d] = lca[lca[k][d-1]][d-1];
         }
      }
   }
   
   public static int[] subsize;                          //size of subtree
   public static int[] vertextosegtree;
   public static int[] topchain;
   public static long[] vals;
   
   public static int stindex;
   
   public static void dfshld(int cur, int top, int p){
      vertextosegtree[cur] = stindex++;
      stupdate(vertextosegtree[cur],vals[cur]);
      topchain[cur] = top;
      int maxchild = -1;
      int maxsize = -1;
      for(Edge nei : adj.get(cur)){
         if(nei.b == p) 
            continue;
         if(subsize[nei.b] > maxsize){
            maxchild = nei.b;
            maxsize = subsize[nei.b];
         }
      }
      if(maxchild < 0) 
         return;
      dfshld(maxchild,top,cur);
      for(Edge nei : adj.get(cur)){
         if(nei.b == p || nei.b == maxchild) 
            continue;
         dfshld(nei.b,nei.b,cur);
      }
   }
   
   public static void dfssize(int v, int p){
      subsize[v]++;
      for(Edge nei : adj.get(v)){
         if(nei.b == p) 
            continue;
         depth[nei.b] = depth[v] + 1;
         lca[nei.b][0] = v;
         dfssize(nei.b,v);
         subsize[v] += subsize[nei.b];
      }
   }
   
   public static void inithld(){
      //dfssize(0,-1);              already done in main
      initlca();
      stindex = 0;
      dfshld(0,0,-1);
   }
   
   public static long pathquery(int c, int p){              //child,parent
      long ret = 0;
      while(c != p){
         if(topchain[c] == c){
            //light edge
            //MAX
            ret = Math.max(ret,vals[c]);
            c = lca[c][0];
         }
         else if(depth[topchain[c]] > depth[p]){
            ret = Math.max(ret,stquery(vertextosegtree[topchain[c]],vertextosegtree[c]));
            c = lca[topchain[c]][0];
         } else {
            ret = Math.max(ret,stquery(vertextosegtree[p]+1,vertextosegtree[c]));
            break;
         }
      }
      return ret;
   }
   
   public static long query(int a, int b){
      int r = getlca(a,b);
      return Math.max(pathquery(a,r),pathquery(b,r));
   }

   
   
   
   
   
   
   
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }
   
   public static class Edge implements Comparable<Edge>{
      int a;
      int b;
      long w;
      public Edge(int d, int e, long c){
         a = d;
         b = e;
         w = c;
      }
      
      public int compareTo(Edge e){
         if(w > e.w){
            return 1;
         } else if(w < e.w){
            return -1;
         }
         return 0;
      }
   }
   
   
   
      
}