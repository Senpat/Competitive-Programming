/*
TASK: cowland
LANG: JAVA
*/
//tutorial
import java.io.*;
import java.util.*;

class cowlandb{
   
   public static int[] costs;
   public static int MAXN = 100005;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowland.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      
      
      //instantiate segtree and hld
      segtree = new int[4*MAXN];
      lca = new int[MAXN][MAXD];
      depth = new int[MAXN];
      
      subsize = new int[MAXN];
      vertextosegtree = new int[MAXN];
      topchain = new int[MAXN];
      vals = new int[MAXN];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         vals[k] = Integer.parseInt(st.nextToken());
      }
      
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         a--;
         b--;
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
         
      inithld();
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         
         int first = Integer.parseInt(st.nextToken());
         if(first == 1){
            //update
            int i = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            i--;
            
            vals[i] = cost;
            stupdate(vertextosegtree[i],cost);
         
         
         } else if(first == 2){
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            a--;
            b--;
            
            int answer = query(a,b);
            // System.out.println(answer);
            out.println(answer);
         
         }
      }
      
      
      
      
        
        
      out.close();
   }
   
   //SEGTREE, HLD, AND LCA FUNCTIONS
   
   //dont forget 0 indexed edges
   public static int[] segtree;
   public final static int MAXD = 17;
   public static int[][] lca;
   public static int[] depth;
   
   public static void stupdate(int index, int l, int r, int i, int x){
      if(l==r) segtree[index] = x;
      else {
         int mid = (l+r)/2;
         if(i <= mid) stupdate(2*index, l, mid, i, x);
         else stupdate(2*index+1, mid+1, r, i, x);
         segtree[index] = segtree[index*2] ^ segtree[index*2 + 1];                   //BITWISE OR
      }
   }
   
   public static void stupdate(int i, int x){
      stupdate(1,0,MAXN-1,i,x);
   }
   
   public static int stquery(int index, int l, int r, int lhs, int rhs){
      if(l >= lhs && r <= rhs) 
         return segtree[index];
      int ret = 0;
      int mid = (l+r)/2;
      if(mid >= lhs) ret ^= stquery(2*index, l, mid, lhs, rhs);
      if(mid+1 <= rhs) ret ^= stquery(2*index+1, mid+1, r, lhs,rhs);
      return ret;
   }
   
   public static int stquery(int l, int r){
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
   public static int[] vals;
   
   public static int stindex;
   
   public static void dfshld(int cur, int top, int p){
      vertextosegtree[cur] = stindex++;
      stupdate(vertextosegtree[cur],vals[cur]);
      topchain[cur] = top;
      int maxchild = -1;
      int maxsize = -1;
      for(int nei : adj.get(cur)){
         if(nei == p) 
            continue;
         if(subsize[nei] > maxsize){
            maxchild = nei;
            maxsize = subsize[nei];
         }
      }
      if(maxchild < 0) 
         return;
      dfshld(maxchild,top,cur);
      for(int nei : adj.get(cur)){
         if(nei == p || nei == maxchild) 
            continue;
         dfshld(nei,nei,cur);
      }
   }
   
   public static void dfssize(int v, int p){
      subsize[v]++;
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         depth[nei] = depth[v] + 1;
         lca[nei][0] = v;
         dfssize(nei,v);
         subsize[v] += subsize[nei];
      }
   }
   
   public static void inithld(){
      dfssize(0,-1);
      initlca();
      stindex = 0;
      dfshld(0,0,-1);
   }
   
   public static int pathquery(int c, int p){              //child,parent
      int ret = 0;
      while(c != p){
         if(topchain[c] == c){
            //light edge
            //BITWISE OR
            ret ^= vals[c];
            c = lca[c][0];
         }
         else if(depth[topchain[c]] > depth[p]){
            ret ^= stquery(vertextosegtree[topchain[c]],vertextosegtree[c]);
            c = lca[topchain[c]][0];
         } else {
            ret ^= stquery(vertextosegtree[p]+1,vertextosegtree[c]);
            break;
         }
      }
      return ret;
   }
   
   public static int query(int a, int b){
      int r = getlca(a,b);
      return pathquery(a,r) ^ pathquery(b,r) ^ vals[r];
   }
         
   
   
      
}