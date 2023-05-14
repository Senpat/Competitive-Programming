//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt
//don't have the ds knowledge to finish, need range update
public class E864{
   
   //for fenwick tree that stores the depths
   public static int[] bits;
   public static int n;
   
   public static int N;
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      N = 5000005;
      int[] phi = new int[N+1];
      for (int i = 0; i <= N; i++)
         phi[i] = i;
   
      for (int i = 2; i <= N; i++) {
         if (phi[i] == i) {
            for (int j = i; j <= N; j += i)
               phi[j] -= phi[j] / i;
         }
      }
      
      
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n+1];
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      //construct tree
      adj = new ArrayList<ArrayList<Integer>>(N+1);
      for(int k = 0; k <= N; k++) adj.add(new ArrayList<Integer>());
      
      HashSet<Integer> added = new HashSet<Integer>();
      
      for(int k = 1; k <= n; k++){
         int i = array[k];
         while(i != 1 && !added.contains(i)){
            adj.get(i).add(phi[i]);
            adj.get(phi[i]).add(i);
            
            i = phi[i];
         }
      }
      
      lca = new int[N+1][MAXD];
      depth = new int[N+1];
      depth[1] = 0;
      initdfs(1,-1);
      initLCA();
      
      bits = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         update(k,depths[k]);
      }
      
      for(int q = 0; q < m; q++){
         st = new StringTokenizer(f.readLine());
         
         int t = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         if(t == 1){
         
         } else {
         
         }
      }
      
      
      
      out.close();
   }

   
   public static class Node{
      int l;
      int r;
      int lca;
      Node lnode;
      Node rnode;
      
      public Node(int a, int b){
         if(l == r){
            lca = array[a];
         } else {
            lnode = new Node(a,a+(b-a)/2);
            rnode = new Node(a+(b-a)/2+1,b);
            
            lca = getlca(lnode.lca,rnode.lca);
         }
      }
   }
   
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }
   
      //lca and dist stuff
   public static int MAXD = 6;
   public static int[][] lca;
   public static int[] depth;
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 0; i < N+1; i++) {
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
      if(a==b) 
         return a;
      
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
         if(nei == p) 
            continue;
         depth[nei] = depth[v]+1;
         lca[nei][0] = v;
         initdfs(nei,v);
      }
   }
      
}