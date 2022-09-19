//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt (fail)
public class C808{

   public static int[] parent;
   public static int[] sizes;

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
   
      StringTokenizer st = new StringTokenizer(f.readLine());
   
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      Edge[] edges = new Edge[m];
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
   
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
         
         edges[k] = new Edge(a,b,k);
      }
      
      //instantiate dsu arrays
      //ONE INDEXED
      parent = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         parent[k] = k;
      }
      
      sizes = new int[n+1];                     //stores depth of every dsu
      Arrays.fill(sizes,1);
      
      boolean[] used = new boolean[m];
      for(int k = 0; k < m; k++){
         if(find(edges[k].from) == find(edges[k].to)) continue;
         used[k] = true;
         union(edges[k].from,edges[k].to);
      }
      
      boolean[] answer = new boolean[n+1];
      for(int k = 1; k <= n; k++){
         boolean stopped = false;
         boolean fail = false;
         for(Edge e : adj.get(k)){
            if(!used[e.w]) stopped = true;
            else {
               if(stopped){
                  fail = true;
                  break;
               }
            }
         }
         
         answer[k] = !fail;
      }
      
      for(int k = 1; k <= n; k++){
         if(answer[k]) out.print("1");
         else out.print("0");
      }
      out.println();
      
      
      
      
      out.close();
   }
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }
   
   
   public static class Edge{
      int from;
      int to;
      int w;
      public Edge(int a, int b, int c){
         from = a;
         to = b;
         w = c;
      }
      public Edge(int a, int b){
         to = a;
         w = b;
      }
   }
      
}