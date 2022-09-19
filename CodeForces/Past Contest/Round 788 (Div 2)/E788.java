//make sure to make new file!
import java.io.*;
import java.util.*;

public class E788{

   public static ArrayList<ArrayList<Edge>> adj;
   public static int[] parents;
   public static int[] color;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int p = Integer.parseInt(f.readLine());
         int n = (1 << p);
      
         adj = new ArrayList<ArrayList<Edge>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
         for(int k = 0; k < n-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(new Edge(b,k));
            adj.get(b).add(new Edge(a,k));
         
         }
         
         parents = new int[n+1];
         color = new int[n+1];
         color[1] = 1;
         dfs(1,-1);
         
         int[] nodes = new int[n+1];
         int[] edges = new int[n-1];
         
         nodes[1] = n;
         for(int k = 2; k <= n; k++){
            if(color[k] == 1){
               nodes[k] = n+k-1;
               edges[parents[k]] = k-1;
            } else {
               edges[parents[k]] = n+k-1;
               nodes[k] = k-1;
            }
         }
         
         out.println("1");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + nodes[k]);
         }
         out.println(sj.toString());
         sj = new StringJoiner(" ");
         for(int k = 0; k < n-1; k++){
            sj.add("" + edges[k]);
         }
         out.println(sj.toString());
         

      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         parents[e.to] = e.i;
         color[e.to] = 3-color[v];
         dfs(e.to,v);
      }
   }
   
   public static class Edge{
      int to;
      int i;
      public Edge(int a, int b){
         to = a;
         i = b;
      }
   }
      
}