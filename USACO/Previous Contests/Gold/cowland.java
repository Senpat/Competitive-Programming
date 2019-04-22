/*
TASK: cowland
LANG: JAVA
*/
//tutorial
import java.io.*;
import java.util.*;

class cowland{
   
   
   public static ArrayList<ArrayList<Integer>> edges;
   
   public static int n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowland.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      
      
      //instantiate segtree and hld
      segtree = new int[4*MAX_N];
      lca = new int[MAX_N][MAX_D];
      depth = new int[MAX_N];
      
      treesz = new int[MAX_N];
      vertextosegtree = new int[MAX_N];
      topchain = new int[MAX_N];
      vals = new int[MAX_N];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         vals[k] = Integer.parseInt(st.nextToken());
      }
      
      
      edges = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) edges.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         a--;
         b--;
         
         edges.get(a).add(b);
         edges.get(b).add(a);
      }
         
      initHLD();
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         
         int first = Integer.parseInt(st.nextToken());
         if(first == 1){
            //update
            int i = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            i--;
            
            vals[i] = cost;
            segtreeupd(vertextosegtree[i],cost);
         
         
         } else if(first == 2){
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            a--;
            b--;
            
            int answer = query(a,b);
            System.out.println(answer);
            out.println(answer);
         
         }
      }
      
      
      
      
        
        
      out.close();
   }
   
   //SEGTREE, HLD, AND LCA FUNCTIONS
   
   //dont forget 0 indexed edges
   public static int MAX_N = 100000;
   public static int[] segtree;
   
   public static void segtreeupd(int idx, int l, int r, int i, int v) {
      if(l == r) segtree[idx] = v;
      else {
         int m = (l+r)/2;
         if(i <= m) segtreeupd(2*idx, l, m, i, v);
         else segtreeupd(2*idx+1, m+1, r, i, v);
         segtree[idx] = segtree[2*idx] ^ segtree[2*idx+1];
      }
   }
   public static void segtreeupd(int i, int v) {
      segtreeupd(1, 0, MAX_N-1, i, v);
   }
   
   public static int segtreeqry(int idx, int l, int r, int lhs, int rhs) {
      if(l >= lhs && r <= rhs) 
         return segtree[idx];
      int ret = 0;
      int m = (l+r)/2;
      if(m >= lhs) ret ^= segtreeqry(2*idx, l, m, lhs, rhs);
      if(m+1 <= rhs) ret ^= segtreeqry(2*idx+1, m+1, r, lhs, rhs);
      return ret;
   }
   public static int segtreeqry(int l, int r) {
      return segtreeqry(1, 0, MAX_N-1, l, r);
   }
   
   public static int MAX_D = 17;
   public static int[][] lca;
   public static int[] depth;
   
   public static int getLCA(int a, int b) {
      if(depth[a] < depth[b]){
         //swap a and b
         int temp = b;
         b = a;
         a = temp;
      }
      for(int d = MAX_D-1; d >= 0; d--) {
         //if(depth[a] - (1<<d) >= depth[b]) {
         if (((depth[a]-depth[b]) & (1<<d)) != 0){
            a = lca[a][d];
         }
      }
      for(int d = MAX_D-1; d >= 0; d--) {
         if(lca[a][d] != lca[b][d]) {
            a = lca[a][d];
            b = lca[b][d];
         }
      }
      if(a != b) {
         a = lca[a][0];
         b = lca[b][0];
      }
      return a;
   }
   
   public static void initLCA() {
      for(int d = 1; d < MAX_D; d++) {
         for(int i = 0; i < MAX_N; i++) {
            lca[i][d] = lca[lca[i][d-1]][d-1];
         }
      }
   }
   
   //vector<int> edges[MAX_N];
   public static int[] treesz;
   public static int[] vertextosegtree;
   public static int[] topchain;
   public static int[] vals;
   
   public static int internalsegtreeidx;
   
   public static void dfsForHLD(int curr, int topPtr, int par) {
      vertextosegtree[curr] = internalsegtreeidx++;
      segtreeupd(vertextosegtree[curr], vals[curr]);
      topchain[curr] = topPtr;
      int largestchild = -1;
      int largestsz = -1;
      for(int out: edges.get(curr)) {
         if(out == par) 
            continue;
         if(treesz[out] > largestsz) {
            largestsz = treesz[out];
            largestchild = out;
         }
      }
      if(largestchild < 0) 
         return;
      dfsForHLD(largestchild, topPtr, curr);
      for(int out: edges.get(curr)) {
         if(out == par || out == largestchild) 
            continue;
         dfsForHLD(out, out, curr);
      }
   }
   
   public static void dfsForSize(int curr, int par) {
      treesz[curr]++;
      for(int out: edges.get(curr)) {
         if(out == par) 
            continue;
         depth[out] = depth[curr] + 1;
         lca[out][0] = curr;
         dfsForSize(out, curr);
         treesz[curr] += treesz[out];
      }
   }
   
   public static void initHLD() {
      dfsForSize(0, -1);
      initLCA();
      internalsegtreeidx = 0;
      dfsForHLD(0, 0, -1);
   }
   
   public static int pathQuery(int child, int par) {
      int ret = 0;
      while(child != par) {
         if(topchain[child] == child) {
         // light edge
            ret ^= vals[child];
            child = lca[child][0];
         }
         else if(depth[topchain[child]] > depth[par]) {
            ret ^= segtreeqry(vertextosegtree[topchain[child]], vertextosegtree[child]);
            child = lca[topchain[child]][0];
         }
         else {
            ret ^= segtreeqry(vertextosegtree[par]+1, vertextosegtree[child]);
            break;
         }
      }
      return ret;
   }
   
   public static int query(int a, int b) {
      int r = getLCA(a, b);
      return pathQuery(a, r) ^ pathQuery(b, r) ^ vals[r];
   }

         
   
   
      
}