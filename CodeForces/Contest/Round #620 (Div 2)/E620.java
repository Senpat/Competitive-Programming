//make sure to make new file!
import java.io.*;
import java.util.*;

public class E620{
   
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
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
      
   
      lca = new int[n+1][MAXD];
      depth = new int[n+1];
      depth[1] = 0;
      initdfs(1,-1);
      initLCA();
      
      int q = Integer.parseInt(f.readLine());
      for(int k = 0; k < q; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         
         
         //adding path(a,b), checking path (x,y)
         int xydist = dist(x,y);
         
         //m < dist(x,y), must use the new path
         if(m < xydist){
            int d1 = dist(x,a)+1+dist(b,y);
            int d2 = dist(x,b)+1+dist(a,y);
            
            if((d1 <= m && d1%2 == m%2) || (d2 <= m && d2%2 == m%2)){
               out.println("YES");
            } else {
               out.println("NO");
            }
            continue;
         }
         
         if(m%2 == xydist%2){
            out.println("YES");
            continue;
         }
         int abdist = dist(a,b);
            
         if(abdist%2 == 0){
            //use path to change parity
            if(dist(x,a)+1+dist(b,y) <= m || dist(x,b)+1+dist(a,y) <= m){
               out.println("YES");
               continue;
            } 
         
         }
         
         out.println("NO");
      }
      
      
      out.close();
   }
   
   
   
   
   //lca and dist stuff
   public static int n;
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
      //System.out.println("lca: of " + a + " " + b + ": " + lca(a,b) + "and dist is: " + (depth[a] + depth[b] - 2*depth[lca(a,b)]));
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