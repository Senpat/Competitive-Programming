//make sure to make new file!
import java.io.*;
import java.util.*;

public class EPR{

   public static int n;
   public static int d;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static boolean[][] targets;
   
   public static boolean[][] hastargetchild;
   
   public static int answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
         
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
            
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
         
      ArrayList<Integer> p1 = new ArrayList<Integer>();
      ArrayList<Integer> p2 = new ArrayList<Integer>();
         
      st = new StringTokenizer(f.readLine());
      int p1n = Integer.parseInt(st.nextToken());
      targets = new boolean[n+1][2];
      for(int k = 0; k < p1n; k++){
         int i = Integer.parseInt(st.nextToken());
         p1.add(i);
         targets[i][0] = true;
      }
         
      st = new StringTokenizer(f.readLine());
      int p2n = Integer.parseInt(st.nextToken());
      for(int k = 0; k < p2n; k++){
         int i = Integer.parseInt(st.nextToken()); 
         p2.add(i);
         targets[i][1] = true;
      }
         
         
      lca = new int[n+1][MAXD];
      depth = new int[n+1];
      depth[1] = 0;
      initdfs(1,-1);
      initLCA();
         
         
      //for every value in p1, add the node d spots above to targets2
         
      for(int i : p1){
         targets[getancestor(i,d)][1] = true;
      }
         
      for(int i : p2){
         targets[getancestor(i,d)][0] = true;
      }
         
      hastargetchild = new boolean[n+1][2];
      filltargetchild(1,-1,0);
      filltargetchild(1,-1,1);
         
      answer = 0;
      dfs(1,-1,0);
      dfs(1,-1,1);
         
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p, int piece){
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         if(hastargetchild[nei][piece]){
            answer++;
            dfs(nei,v,piece);
            answer++;
         }
      
      }
      
   }
   
   public static void filltargetchild(int v, int p, int piece){
      
      if(targets[v][piece]) hastargetchild[v][piece] = true;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         filltargetchild(nei,v,piece);
         
         hastargetchild[v][piece] |= hastargetchild[nei][piece];
      }
      
      
   }
   
   
   //lca and dist stuff
   public static int MAXD = 18;
   public static int[][] lca;
   public static int[] depth;
   
   public static int getancestor(int v, int x){
      int i = (1 << MAXD);
      int ind = MAXD;
      while(x > 0){
         if(i <= x){
            v = lca[v][ind];
            x -= i;
         }
         ind--;
         i >>= 1;
      }
      
      return v;      
      
   }
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 1; i <= n; i++) {
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