//The Fair Nut and the Best Path
import java.io.*;
import java.util.*;
//solution #1
public class D1084b{

   public static long[] costs,best;
   public static ArrayList<ArrayList<Edge>> adj;
   public static long answer = 0L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      costs = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         costs[k] = Long.parseLong(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
      
         adj.get(a).add(new Edge(b,c));
         adj.get(b).add(new Edge(a,c));
      }
      
      best = new long[n+1];
      
      
      dfs(1,-1);
      
      out.println(answer);
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      long b1 = 0L;
      long b2 = 0L;
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         dfs(e.to,v);
         long cur = best[e.to] - e.w;
         if(cur > b1){
            b2 = b1;
            b1 = cur;
         } else if(cur > b2){
            b2 = cur;
         }
      }
      
      answer = Math.max(answer, costs[v] + b1 + b2);
      best[v] = Math.max(best[v],costs[v] + b1);
      answer = Math.max(answer,best[v]);
         
         
      
   }
      
   
   
   public static class Edge{
      int to;
      long w;
      
      public Edge(int a, long b){
         to = a;
         w = b;
      }
   }
   
}