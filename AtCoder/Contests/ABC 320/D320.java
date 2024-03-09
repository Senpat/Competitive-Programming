//make sure to make new file!
import java.io.*;
import java.util.*;

public class D320{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static long[][] answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         long y = Long.parseLong(st.nextToken());
      
         adj.get(a).add(new Edge(b,x,y));
         adj.get(b).add(new Edge(a,-x,-y));
      }
      
      answer = new long[n+1][2];
      
      for(int k = 2; k <= n; k++){
         answer[k][0] = Long.MAX_VALUE;
         answer[k][1] = Long.MAX_VALUE;
      }
      
      dfs(1);
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         if(answer[k][0] == Long.MAX_VALUE){
            sj.add("undecidable");
         } else {
            sj.add("" + answer[k][0] + " " + answer[k][1]);
         }
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      
      for(Edge e : adj.get(v)){
         if(answer[e.to][0] != Long.MAX_VALUE) continue;
         answer[e.to][0] = answer[v][0] + e.x;
         answer[e.to][1] = answer[v][1] + e.y;
         
         dfs(e.to);
      }
      
   }
   
   public static class Edge{
      int to;
      long x;
      long y;
      
      public Edge(int a, long b, long c){
         to = a;
         x = b;
         y = c;
      }
   }
      
}