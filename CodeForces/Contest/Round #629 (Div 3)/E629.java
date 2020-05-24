//make sure to make new file!
import java.io.*;
import java.util.*;

public class E629{
   
   public static ArrayList<HashSet<Integer>> adj;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<HashSet<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      lca = new int[n+1][MAXD];
      depth = new int[n+1];
      depth[1] = 0;
      initdfs(1,-1);
      initLCA();
      
      for(int q = 0; q < m; q++){
         
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         
         int[] array = new int[i];
         
         int maxdepth = -1;
         int node = -1;           //deepest node
         for(int k = 0; k < i; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(depth[array[k]] > maxdepth){
               maxdepth = depth[array[k]];
               node = array[k];
            }
         }
         //out.println(node);
         
         if(check(array,node)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      

      
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int node){
      for(int k = 0; k < array.length; k++){
         if(array[k] == node) continue;
         int lca = lca(array[k],node);
         if(lca == array[k] || adj.get(lca).contains(array[k])) continue;
         return false;
      }
      return true;
   }
   
   //lca and dist stuff
   public static int MAXD = 19;
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