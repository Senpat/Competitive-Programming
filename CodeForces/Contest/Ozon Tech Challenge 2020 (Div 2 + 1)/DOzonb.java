//make sure to make new file!
import java.io.*;
import java.util.*;
//semi-t
public class DOzonb{
   
   public static int n;
   public static boolean[][] edges;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      int vertex = 1;
      
      HashSet<Integer> nodes = new HashSet<Integer>();
      for(int k = 1; k <= n; k++){
         nodes.add(k);
      }
      
      while(true){
         if(nodes.size() == 1){
            int i = -1;
            for(int node : nodes){
               i = node;
            }
            out.println("! " + i);
            break;
         }           
            
         
         //find two leaves
         int v2 = 0;
         int v3 = 0;
         
         Stack<Integer> q = new Stack<Integer>();
         
         q.add(vertex);
         
      
         boolean[] seen = new boolean[n+1];
         seen[vertex] = true;
         while(!q.isEmpty()){
            int s = q.pop();
            
            if(adj.get(s).size() == 1){
               if(v2 == 0){
                  v2 = s;
               } else {
                  v3 = s;
                  break;
               }
            }
         
            for(int nei : adj.get(s)){
               if(!seen[nei]){
                  q.add(nei);
                  seen[nei] = true;
               }
            }
         }
         
         
         out.println("? " + v2 + " " + v3);
         out.flush();
         
         int ans = Integer.parseInt(f.readLine());
         
         
         if(ans == v2 || ans == v3){
            out.println("! " + ans);
            break;
         }
         
         
         nodes.remove(v2);
         nodes.remove(v3);
         for(int v = 1; v <= n; v++){
            adj.get(v).remove(v2);
            adj.get(v).remove(v3);
         }
         
         
         
         for(int node : nodes){
            vertex = node;
            break;
         }
         
      }
   
      
      
      
      
      
      out.close();
   }
      
   public static int farthestpoint(int vertex){
      Stack<State> q = new Stack<State>();
         
      q.add(new State(vertex,0));
         
      int maxdis = -1;
      int maxvertex = -1;
      
      boolean[] seen = new boolean[n+1];
      seen[vertex] = true;
      while(!q.isEmpty()){
         State s = q.pop();
         
         if(s.d > maxdis){
            maxdis = s.d;
            maxvertex = s.v;
         }
         
         for(int k = 1; k <= n; k++){
            if(k != s.v && !seen[k] && edges[s.v][k]){
               q.add(new State(k,s.d+1));
               seen[k] = true;
            }
         }
      }
      
      return maxvertex;
   }
      
      
   
   
   public static class State{
      int v;
      int d;
      public State(int a, int b){
         v = a;
         d = b;
      }
   }
      
}