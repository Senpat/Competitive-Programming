/*
TASK: butter
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class butter{
   
   public static int[] cows;
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("butter.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int c = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      cows = new int[n+1];
      for(int k = 0; k < c; k++){
         int i = Integer.parseInt(f.readLine());
         cows[i]++;
      }
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,w));
         adj.get(b).add(new Edge(a,w));
         
      }
      
      int min = Integer.MAX_VALUE;
      for(int root = 1; root <= n; root++){
         int[] dists = new int[n+1];
         Arrays.fill(dists,Integer.MAX_VALUE);
         dists[root] = 0;
      
         PriorityQueue<State> pq = new PriorityQueue<State>();
         pq.add(new State(root,0));
         
         while(!pq.isEmpty()){
            State s = pq.poll();
            
            if(dists[s.v] != s.d) continue;
            
            for(Edge e : adj.get(s.v)){
               int newd = dists[s.v] + e.w;
               if(newd < dists[e.to]){
                  dists[e.to] = newd;
                  pq.add(new State(e.to,newd));
               }
            }
         }
         
         int sum = 0;
         for(int k = 1; k <= n; k++){
            sum += cows[k] * dists[k];
         }
         min = Math.min(min,sum);
      }
      
      out.println(min);
      
        
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;
      int d;
      public State(int a, int b){
         v = a;
         d = b;
      }
      public int compareTo(State s){
         return d-s.d;
      }
   }
   
   public static class Edge{
      int to;
      int w;
      public Edge(int a, int b){
         to = a;
         w = b;
      }
   }
   
        
}