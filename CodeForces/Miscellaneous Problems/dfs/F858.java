//make sure to make new file!
import java.io.*;
import java.util.*;
//https://usaco.guide/problems/cf-wizards-tour/solution
public class F858{

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] parent;
   public static boolean[] seen;
   public static boolean[] span;          //does if edge is in spanning tree
   
   public static boolean[] used;          //for non-span edges, if that edge has been used
   
   public static ArrayList<Triple> answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
      }
      
      //get spanning tree
      parent = new int[n+1];
      Arrays.fill(parent,-1);
      seen = new boolean[n+1];
      span = new boolean[m];
      ArrayList<Integer> roots = new ArrayList<Integer>();
      
      for(int root = 1; root <= n; root++){
         if(seen[root]) continue;
         seen[root] = true;   
         dfs(root);
         roots.add(root);
      }
      answer = new ArrayList<>();
      
      used = new boolean[m];
      
      for(int root : roots){
         dfs2(root);
      }
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + answer.size());
      for(Triple t : answer){
         sj.add(t.toString());
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean dfs2(int v){              //returns if edge from b to parent was used
      
      ArrayList<Integer> children = new ArrayList<Integer>();
      
      for(Edge e : adj.get(v)){
         if(e.to == parent[v]) continue;
         if(used[e.i]) continue;
         
         used[e.i] = true;
         
         if(span[e.i]){
            boolean eused = dfs2(e.to);
            if(!eused) children.add(e.to);
         } else {
            children.add(e.to);
         }
           
      }
      
      for(int k = 0; k+1 < children.size(); k+=2){
         answer.add(new Triple(children.get(k),v,children.get(k+1)));
      }
      
      if(children.size() % 2 == 1){
         if(parent[v] != -1){
            answer.add(new Triple(children.get(children.size()-1),v,parent[v]));
         }
         return true;
      } else {
         return false;
      }
   }
   
   public static void dfs(int v){
      for(Edge e : adj.get(v)){
         if(seen[e.to]) continue;
         span[e.i] = true;
         seen[e.to] = true;
         parent[e.to] = v;
         dfs(e.to);
      }
   }
   
   
   public static class Triple{
      int a;
      int b;
      int c;
      public Triple(int x, int y, int z){
         a = x;
         b = y;
         c = z;
      }
      public String toString(){
         return a + " " + b + " " + c;
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