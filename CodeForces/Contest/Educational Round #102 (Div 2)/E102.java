//make sure to make new file!
import java.io.*;
import java.util.*;

public class E102{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long w = Long.parseLong(st.nextToken());
      
         adj.get(a).add(new Edge(b,w));
         adj.get(b).add(new Edge(a,w));
      }
      
      long[][][] dists = new long[n+1][2][2];
      for(int k = 0; k <= n; k++){
         for(int j = 0; j < 2; j++){
            for(int h = 0; h < 2; h++){
               dists[k][j][h] = Long.MAX_VALUE;
            }
         }
      }
      
      dists[1][0][0] = 0;
      PriorityQueue<State> pq = new PriorityQueue<State>();
      pq.add(new State(1,0,0,0));
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(dists[s.v][s.maxnadded][s.minadded] != s.d) continue;
         
         for(Edge e : adj.get(s.v)){
            //neither
            long newd1 = dists[s.v][s.maxnadded][s.minadded]+e.w;
            if(newd1 < dists[e.to][s.maxnadded][s.minadded]){
               dists[e.to][s.maxnadded][s.minadded] = newd1;
               pq.add(new State(e.to,s.maxnadded,s.minadded,newd1));
            }
            //don't add (max)
            if(s.maxnadded == 0){
               long newd2 = dists[s.v][s.maxnadded][s.minadded];
               if(newd2 < dists[e.to][1][s.minadded]){
                  dists[e.to][1][s.minadded] = newd2;
                  pq.add(new State(e.to,1,s.minadded,newd2));
               }
            }
            //add (min)
            if(s.minadded == 0){
               long newd3 = dists[s.v][s.maxnadded][s.minadded] + 2*e.w;
               if(newd3 < dists[e.to][s.maxnadded][1]){
                  dists[e.to][s.maxnadded][1] = newd3;
                  pq.add(new State(e.to,s.maxnadded,1,newd3));
               }
            }
            //both (add and subtract, same as adding it once)
            if(s.maxnadded == 0 && s.minadded == 0){
               long newd4 = dists[s.v][s.maxnadded][s.minadded] + e.w;
               if(newd4 < dists[e.to][1][1]){
                  dists[e.to][1][1] = newd4;
                  pq.add(new State(e.to,1,1,newd4));
               }
            }
         }n
         
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 2; k <= n; k++){
         sj.add("" + dists[k][1][1]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;
      int maxnadded;             //max not added
      int minadded;              //min added
      long d;
      public State(int a, int b, int c, long dd){
         v = a;
         maxnadded = b;
         minadded = c;
         d = dd;
      }
      public int compareTo(State s){
         if(d > s.d) return 1;
         if(d < s.d) return -1;
         return 0;
      }
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