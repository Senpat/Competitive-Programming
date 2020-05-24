//make sure to make new file!
import java.io.*;
import java.util.*;
//tle tc 16
public class DOzon{
   
   public static int n;
   public static boolean[][] edges;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      
      edges = new boolean[n+1][n+1];
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         edges[a][b] = true;
         edges[b][a] = true;
      }
      
      int vertex = 1;
      
      /*
      HashSet<Integer> nodes = new HashSet<Integer>();
      for(int k = 1; k <= n; k++){
         nodes.add(k);
      }*/
      
      boolean[] deleted = new boolean[n+1];
      int delnodes = 0;
      
      while(true){
         if(n-delnodes == 1){
            for(int k = 1; k <= n; k++){
               if(!deleted[k]){
                  out.println("! " + k);
                  break;
               }
            }
            break;
         }
         
         //find diameter of tree
         
         //find longest point from vertex
         int v2 = farthestpoint(vertex);
         int v3 = farthestpoint(v2);
        
         
         
         out.println("? " + v2 + " " + v3);
         out.flush();
         
         int ans = Integer.parseInt(f.readLine());
         
         
         if(ans == v2 || ans == v3){
            out.println("! " + ans);
            break;
         }
         
         delnodes += 2;
         deleted[v2] = true;
         deleted[v3] = true;
         
         for(int k = 1; k <= n; k++){
            edges[v2][k] = false;
            edges[k][v2] = false;
            edges[v3][k] = false;
            edges[k][v3] = false;
         }
         
         
         /*
         //find all points between v2 and v3
         
         stack = new Stack<Integer>();
         seendfs = new boolean[n+1];
         
         dfs(v2,v3);
         
         //remove points
         while(!stack.isEmpty()){
            int i = stack.pop();
            if(i != ans){
               for(int k = 1; k <= n; k++){
                  edges[i][k] = false;
                  edges[k][i] = false;
               }
            }
         }
         
         int neighbors = 0;
         for(int k = 0; k <= n; k++){
            if(edges[ans][k]) neighbors++;
         }
         
         if(neighbors == 0){
            out.println("! " + ans);
            break;
            
         }
         */
         vertex = ans;
         
      }
   
      
      
      
      
      
      out.close();
   }
   
   public static Stack<Integer> stack;
   public static boolean[] seendfs;
   
   public static void dfs(int x,int y){
      stack.add(x); 
      if (x == y) { 
         return; 
      } 
      seendfs[x] = true; 
   
      int flag = 0; 
      for (int j = 1; j <= n; j++) { 
         if (edges[x][j] && !seendfs[j]) { 
            dfs(j,y); 
            flag = 1; 
         } 
      } 
     
      if (flag == 0) {
         stack.pop(); 
      } 
    
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