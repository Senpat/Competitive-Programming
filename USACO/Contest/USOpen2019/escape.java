/*
TASK: escape
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class escape{

   
   public static ArrayList<ArrayList<Edge>> adj;
   public static int[] parent;
   public static int[] sizes;
   public static Edge[] edges; 
   public static int MAXN = 200000;  
   public static long MOD = 1000000007;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("escape.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("escape.out")));
      
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      //find minimum spanning tree
      
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
      adj = new ArrayList<ArrayList<Edge>>(n*m);
      for(int k = 0; k < n*m; k++) adj.add(new ArrayList<Edge>());
      
      
      long minweight = Long.MAX_VALUE;
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m-1; j++){
            long a = Long.parseLong(st.nextToken());
            //adj.get(k*m+j).add(new Edge(k*m+j,k*m+j+1,a));
            //adj.get(k*m+j+1).add(new Edge(k*m+j+1,k*m+j,a));
            pq.add(new Edge(k*m+j,k*m+j+1,a));
            //System.out.println((k*m+j) + " " + (k*m+j+1));
            minweight = Math.min(minweight,a);
         }
      }
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n-1; j++){
            long a = Long.parseLong(st.nextToken());
            //adj.get(k*m+j).add(new Edge(k*m+j,k*m+m+j,a));
            //adj.get(k*m+m+j).add(new Edge(k*m+m+j,k*m+j,a));
            pq.add(new Edge(j*m+k,j*m+m+k,a));
            minweight = Math.min(minweight,a);
         }
      }
      
      long sum = 0L;
      
      parent = new int[MAXN];
      sizes = new int[MAXN];
      
      for(int k = 0; k < MAXN; k++) parent[k] = k;
      Arrays.fill(sizes,1);
           
      ArrayList<Edge> usededges = new ArrayList<Edge>();
      
      ArrayList<Edge> queryedges = new ArrayList<Edge>();
      while(!pq.isEmpty()){
         Edge e = pq.poll();
         //System.out.println(e);
         if(find(e.a) != find(e.b)){
            union(e.a,e.b);
            adj.get(e.a).add(new Edge(e.a,e.b,e.w));
            adj.get(e.b).add(new Edge(e.b,e.a,e.w));
            usededges.add(e);
            sum += e.w;
            
         } else if(e.w == minweight){
            queryedges.add(e);
         }
      }
      
      
      //System.out.println(sum);
      
      
      
      
      
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
         if(e.w > minweight) 
            continue;
         if(depth[e.a] > depth[e.b]){
            vals[e.a] = 1;
         } else {
            vals[e.b] = 1;
         }
      }
      
      /*
      for(int k = 0; k < n*m; k++){
         System.out.println(vals[k]);
      }*/
      
      inithld();
      //System.out.println("done");
      long answer = 1;
      for(Edge e : queryedges){
         answer = (answer + query(e.a,e.b) + MOD)%MOD;
      }
      
      System.out.println(answer);
      out.println(answer);
      
      
      
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
         segtree[index] = (segtree[index*2]+segtree[index*2 + 1]+MOD)%MOD;                   //sum edge query
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
      if(mid >= lhs) ret = (ret + stquery(2*index, l, mid, lhs, rhs)+MOD)%MOD;
      if(mid+1 <= rhs) ret = (ret + stquery(2*index+1, mid+1, r, lhs,rhs)+MOD)%MOD;
      return ret;
   }
   
   public static long stquery(int l, int r){
      return stquery(1,0,MAXN,l,r);
   }
   
   public static int getlca(int a, int b){
      //System.out.println("hi");
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
            //SUM
            ret = (ret+vals[c]+MOD)%MOD;
            c = lca[c][0];
         }
         else if(depth[topchain[c]] > depth[p]){
            ret = (ret+stquery(vertextosegtree[topchain[c]],vertextosegtree[c])+MOD)%MOD;
            c = lca[topchain[c]][0];
         } else {
            ret = (ret+stquery(vertextosegtree[p]+1,vertextosegtree[c])+MOD)%MOD;
            break;
         }
      }
      return ret;
   }
   
   public static long query(int a, int b){
      int r = getlca(a,b);
      //System.out.println(a + " " + b + " " + r);
      return (pathquery(a,r)+pathquery(b,r)+MOD)%MOD;
   }

   
   
   
   
   
   
   
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) 
         return v;
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
      
      public String toString(){
         return a + " " + b + " " + w;
      }
   }
}