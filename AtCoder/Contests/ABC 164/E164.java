//make sure to make new file!
import java.io.*;
import java.util.*;

public class E164{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int sc = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());          //cost
         long t = Long.parseLong(st.nextToken());          //time
         
         adj.get(a).add(new Edge(b,c,t));
         adj.get(b).add(new Edge(a,c,t));
         
      }
      
      int[] ex = new int[n+1];            //how many silvers coins
      int[] ext = new int[n+1];           //exchange time
      
      for(int k = 1; k <= n; k++){
         st = new StringTokenizer(f.readLine());
         
         ex[k]  = Integer.parseInt(st.nextToken());
         ext[k] = Integer.parseInt(st.nextToken());
      }
      
      int MAX = 2600;
      long[][] dists = new long[n+1][MAX+1];
      for(int k = 0; k <= n; k++) Arrays.fill(dists[k],Long.MAX_VALUE);
      
      dists[1][Math.min(MAX,sc)] = 0L;
      /*
      boolean[] seen = new boolean[n+1][MAX+1];
      seen[1][Math.min(MAX,sc)] = true;
      */
      PriorityQueue<State> pq = new PriorityQueue<State>();
      pq.add(new State(1,sc,0L));
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(dists[s.node][Math.min(MAX,s.coins)] != s.time) continue;
         
         
         if(s.coins < MAX){
            //exchange once
            if(dists[s.node][Math.min(MAX,s.coins + ex[s.node])] > s.time + ext[s.node]){
               dists[s.node][Math.min(MAX,s.coins + ex[s.node])] = s.time + ext[s.node];
               pq.add(new State(s.node,s.coins+ex[s.node],s.time+ext[s.node]));
            }
            //go to next node
            for(Edge e : adj.get(s.node)){
               if(e.c <= s.coins){
                  if(dists[e.to][s.coins-e.c] > s.time + e.t){
                     dists[e.to][s.coins-e.c] = s.time + e.t;
                     pq.add(new State(e.to,s.coins-e.c,s.time + e.t));
                  }
               }
            }
         } else {
            //go to next node
            for(Edge e : adj.get(s.node)){
               if(dists[e.to][MAX] > s.time + e.t){
                  dists[e.to][MAX] = s.time + e.t;
                  pq.add(new State(e.to,MAX,s.time + e.t));
               }
            }
         }
      }
      
      long[] answer = new long[n+1];
      
      for(int k = 2; k <= n; k++){
         answer[k] = Long.MAX_VALUE;
         
         for(int j = 0; j <= MAX; j++){
            answer[k] = Math.min(answer[k],dists[k][j]);
         }
      }
      
      for(int k = 2; k <= n; k++){
         out.println(answer[k]);
      }
         
         
         
         
         
            
      
      
      
      
      
         
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int node;
      int coins;
      long time;
      public State(int a, int b, long c){
         node = a;
         coins = b;
         time = c;
      }
      public int compareTo(State s){
         if(time > s.time) return 1;
         if(time < s.time) return -1;
         return 0;
      }
   }
   
   public static class Edge{
      int to;
      int c;
      long t;
      public Edge(int x, int y, long z){
         to = x;
         c = y;
         t = z;
      }
   }
   
   
      
}