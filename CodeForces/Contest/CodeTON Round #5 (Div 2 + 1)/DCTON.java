//make sure to make new file!
import java.io.*;
import java.util.*;

public class DCTON{

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static boolean[] compcheck;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Edge>());
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         
         adj.get(a).add(new Edge(b,c,k));
         adj.get(b).add(new Edge(a,c,k));
      }
      
      //check if 1 and n are in the same component
      compcheck = new boolean[n+1];
      dfs(1);

      
      if(!compcheck[n]){
         out.println("inf");
         out.close();
         return;
      }
      
      ArrayList<String> answer = new ArrayList<String>();
      
      long t = 0;
      boolean[] seen = new boolean[n+1];
      seen[1] = true;
      
      boolean[] edgesseen = new boolean[m];
      PriorityQueue<State> pq = new PriorityQueue<State>();
      for(Edge e : adj.get(1)){
         pq.add(new State(e.v,e.to,e.i));
         edgesseen[e.i] = true;
      }
      
      while(!seen[n]){
         //guaranteed to be more edges in pq
         State s = pq.poll();
         
         //do current state for s.t - t
         answer.add(getstate(seen,s.t-t));
         t = s.t;
         seen[s.to] = true;
         
         for(Edge e : adj.get(s.to)){
            if(edgesseen[e.i]) continue;
            if(seen[e.to]) continue;
            pq.add(new State(t+e.v,e.to,e.i));
            edgesseen[e.i] = true;  
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add(t + " " + answer.size());
      for(String s : answer){
         sj.add(s);
      }
      out.println(sj.toString());
         
      
      
      
      
      
      
      out.close();
   }
   
   public static String getstate(boolean[] seen, long t){
      StringJoiner sj = new StringJoiner("");
      for(int k = 1; k < seen.length; k++){
         if(seen[k]) sj.add("1");
         else sj.add("0");
      }
      
      sj.add(" " + t);
      
      return sj.toString();

   }
   
   public static void dfs(int v){
      compcheck[v] = true;
      
      for(Edge e : adj.get(v)){
         if(compcheck[e.to]) continue;
         dfs(e.to);
      }
   }
   
   public static class State implements Comparable<State>{
      long t;
      int to;
      int ei;     //edge index
      public State(long a, int b, int c){
         t = a;
         to = b;
         ei = c;
      }
      
      public int compareTo(State s){
         if(t > s.t) return 1;
         else if(t < s.t) return -1;
         return 0;
      }
   }
   
   public static class Edge{
      int to;
      long v;
      int i;
      public Edge(int a, long b, int c){
         to = a;
         v = b;
         i = c;
      }
   }
      
}