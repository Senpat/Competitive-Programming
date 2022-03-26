//make sure to make new file!
import java.io.*;
import java.util.*;

public class F550b{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] color;
   public static HashSet<Integer> seen;
   public static boolean fail;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Edge[] edges = new Edge[m];
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
         
         edges[k] = new Edge(a,b);
      }
      
      color = new int[n+1];
      Arrays.fill(color,-1);
      seen = new HashSet<Integer>();
      
      fail = false;
      color[1] = 0;
      seen.add(1);
      dfs(1);
      
      if(fail){
         out.println("NO");
      } else {
         int[] answer = new int[m];
         for(int k = 0; k < m; k++){
            if(color[edges[k].a] == 0 && color[edges[k].b] == 1){
               answer[k] = 1;
            }
         }
         
         out.println("YES");
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < m; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v){
      
      for(int nei : adj.get(v)){
         if(color[nei] != -1 && color[nei] == color[v]){
            fail = true;
            return;
         }
         if(seen.contains(nei)) continue;
         seen.add(nei);
         color[nei] = 1-color[v];
         dfs(nei);
      }
   }
   
   public static class Edge{
      int a;
      int b;
      public Edge(int c, int d){
         a = c;
         b = d;
      }
   }
      
}