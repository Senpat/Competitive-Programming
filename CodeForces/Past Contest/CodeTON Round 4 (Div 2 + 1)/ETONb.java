//make sure to make new file!
import java.io.*;
import java.util.*;

public class ETONb{
   
   public static int n;
   public static int[] parent;
   public static int[] sizes;
   
   public static int[] array;
   
   public static ArrayList<ArrayList<Integer>> kadj;
   
   public static int[] nodeweights;
   public static int[] subsize;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         st = new StringTokenizer(f.readLine());
         array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Edge[] edges = new Edge[m];
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            edges[k] = new Edge(a,b,Math.max(array[a],array[b]));
         }
         
         
         //instantiate dsu arrays
         //ONE INDEXED
         parent = new int[2*n];
      
         for(int k = 1; k < 2*n; k++){
            parent[k] = k;
         }
         
         sizes = new int[2*n];                     //stores depth of every dsu
         Arrays.fill(sizes,1);
      
         //generate kruskals tree
         Arrays.sort(edges);
         
         kadj = new ArrayList<ArrayList<Integer>>(2*n);
         for(int k = 0; k < 2*n; k++) kadj.add(new ArrayList<Integer>());
         
         int node = n+1;
         nodeweights = new int[2*n];
         for(int k = 1; k <= n; k++) nodeweights[k] = array[k];
         
         for(int k = 0; k < m; k++){
            int fa = find(edges[k].a);
            int fb = find(edges[k].b);
            if(fa == fb) 
               continue;
            
            union(fa,node);
            union(fb,node);
            
            kadj.get(fa).add(node);
            kadj.get(node).add(fa);
            kadj.get(fb).add(node);
            kadj.get(node).add(fb);
            
            nodeweights[node] = edges[k].w;
            
            node++;
         }
         
         //not a connected component
         if(kadj.get(2*n-1).size() == 0){
            out.println("NO");
            continue;
         }
         
         subsize = new int[2*n];
         boolean answer = checkdfs(2*n-1,-1);
         
         if(answer){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      
      
      
      out.close();
   }
   
   public static boolean checkdfs(int v, int p){
      subsize[v] = 0;
      if(v <= n) subsize[v] = 1;
      
      boolean found = false;
      int children = 0;
      for(int nei : kadj.get(v)){
         if(nei == p) 
            continue;
         
         found |= checkdfs(nei,v) && subsize[nei] >= nodeweights[v];
         
         subsize[v] += subsize[nei];
         children++;
      }
      
      if((children > 0 && !found) || subsize[v]-1 < nodeweights[v]) 
         return false;
      return true;
   }
   
   public static class Edge implements Comparable<Edge>{
      int a;
      int b;
      int w;
      public Edge(int x, int y, int z){
         a = x;
         b = y;
         w = z;
      }
      public int compareTo(Edge e){
         return w-e.w;
      }
   } 
      
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      if(findv == findu) return;
      parent[findu] = findv;
      sizes[findv] += sizes[findu];
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
}