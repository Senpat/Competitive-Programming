/*
TASK: dining
LANG: JAVA
*/
//using multi-source djikstra
import java.io.*;
import java.util.*;

class dining{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("dining.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(new Edge(b,w));
         adj.get(b).add(new Edge(a,w));
      }
      
      int[] haybales = new int[n+1];
      
      for(int k = 0; k < h; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         haybales[a] = Math.max(haybales[a],b);
      }
      
      int[] d1 = new int[n+1];
      Arrays.fill(d1,Integer.MAX_VALUE);
      d1[n] = 0;
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      
      pq.add(new State(0,n));
      
      while(!pq.isEmpty()){
         State cur = pq.poll();
         int dis = cur.dis;
         int v = cur.v;
         
         for(Edge e : adj.get(v)){
            int to = e.to;
            int len = e.len;
            
            if(d1[v] + len < d1[to]){
               d1[to] = d1[v] + len;
               pq.add(new State(d1[to],to));
            }
         }
      }
      
      
      
      boolean[] reach = new boolean[n+1];
      
      pq = new PriorityQueue<State>();
      
      for(int k = 0; k <= n; k++){
         if(haybales[k] > 0){
            pq.add(new State(-1*(haybales[k]),k));
         }
      }
      
      int[] d = new int[n+1];
      
      while(!pq.isEmpty()){
         State cur = pq.poll();
         int dis = cur.dis;
         int v = cur.v;
         
         if(dis > 0) continue;
         reach[v] = true;
         d[v] = dis;
         for(Edge e : adj.get(v)){
            int to = e.to;
            int len = e.len + d1[v] - d1[to];
            
            if(d[to] > dis + len){
               d[to] = dis+len;
               pq.add(new State(dis+len,to));
            }
         }
      }
      
      for(int k = 1; k < n; k++){
         if(reach[k]){
            System.out.println("1");
            out.println("1");
         } else {
            System.out.println("0");
            out.println("0");
         }
      }
               
               
        
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int dis;
      int v;
      
      public State(int a, int b){
         dis = a;
         v = b;
      }
      
      public int compareTo(State s){
         return dis - s.dis;
      }
      public String toString(){
         return dis + " " + v;
      }
   }
   
   public static class Edge{
      int to;
      int len;
      public Edge(int a, int b){
         to = a;
         len = b;
      }
      public String toString(){
         return to + " " + len;
      }
      
   }
      
}