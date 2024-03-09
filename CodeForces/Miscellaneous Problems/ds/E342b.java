//make sure to make new file!
import java.io.*;
import java.util.*;
//practice new implementation
public class E342b{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static boolean[] marked;
   
   public static int[] subsize;        //gets overridden a lot
   
   public static ArrayList<ArrayList<Anc>> ancestors;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      ancestors = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
         ancestors.add(new ArrayList<Anc>());
      }
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      marked = new boolean[n+1];
      subsize = new int[n+1];
      create_tree(1);
      
      int[] dist = new int[n+1];
      Arrays.fill(dist,Integer.MAX_VALUE);
      
      for(Anc anc : ancestors.get(1)){
         dist[anc.i] = Math.min(dist[anc.i],anc.d);
      }
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         int qt = Integer.parseInt(st.nextToken());
         int i = Integer.parseInt(st.nextToken());
         
         if(qt == 1){
            for(Anc anc : ancestors.get(i)){
               dist[anc.i] = Math.min(dist[anc.i],anc.d);
            }
         } else {
            int d = Integer.MAX_VALUE;
            for(Anc anc : ancestors.get(i)){
               if(dist[anc.i] == Integer.MAX_VALUE) continue;
               d = Math.min(d,dist[anc.i] + anc.d);
            }
            
            out.println(d);
         }
      }
      
      
      
      
      out.close();
   }
   
   
   public static void dfs_size(int v, int p){
      subsize[v] = 1;
      for(int nei : adj.get(v)){
         if(nei == p || marked[nei]) continue;
         dfs_size(nei,v);
         subsize[v] += subsize[nei];
      }
   }
   
   public static int dfs_centroid(int v, int p, int sz){
      for(int nei : adj.get(v)){
         if(nei == p || marked[nei]) continue;
         if(subsize[nei] > sz/2){
            return dfs_centroid(nei,v,sz);
         }
      }
      
      return v;
   }
   
   public static void dfs_fill(int v, int p, int centroid, int d){
      ancestors.get(v).add(new Anc(centroid,d));
      
      for(int nei : adj.get(v)){
         if(nei == p || marked[nei]) continue;
         dfs_fill(nei,v,centroid,d+1);
      }
   }
   
   public static void create_tree(int v){
      dfs_size(v,-1);
      
      int sz = subsize[v];
      int centroid = dfs_centroid(v,-1,sz);
      
      dfs_fill(centroid,-1,centroid,0);
      
      marked[centroid] = true;
      for(int nei : adj.get(centroid)){
         if(marked[nei]) continue;
         create_tree(nei);
      }
   }
   
   public static class Anc{
      int i;
      int d;
      public Anc(int a, int b){
         i = a;
         d = b;
      }
   }
   
      
}