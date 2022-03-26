//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         n = Integer.parseInt(f.readLine());
      
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < n-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
      
         int[] p = new int[n];
         for(int k = 0; k < n; k++){
            p[k] = Integer.parseInt(f.readLine());
         }
         
         
         lca = new int[n+1][MAXD];
         lca[1][0] = 1;
         depth = new int[n+1];
         depth[1] = 0;
         initdfs(1,-1);
         initLCA();
         
         boolean fail = false;
         for(int k = 0; k < n-1; k++){
            //out.println(p[k] + " " + p[k+1] + " " + dist(p[k],p[k+1]));
            if(dist(p[k],p[k+1]) > 3){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println(0);
         } else {
            out.println(1);
         }
      
      }
      
      
      
      
      out.close();
   }
   
      //lca and dist stuff
   public static int MAXD = 18;
   public static int[][] lca;
   public static int[] depth;
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 1; i < n+1; i++) {
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
      //System.out.println("lca: " + a + " " + b + " " + lca(a,b));
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