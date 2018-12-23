/*
TASK: dining
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class diningb{
   
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
      
      int[] d = new int[n+1];
      Arrays.fill(d,Integer.MAX_VALUE);
      d[n] = 0;
      int[] dh = new int[n+1];                           //already eaten haybale
      Arrays.fill(dh,Integer.MAX_VALUE);
      dh[n] =0;
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      
      pq.add(new State(0,n,false));
      
      while(!pq.isEmpty()){
         State cur = pq.poll();
         int dis = cur.dis;
         int v = cur.v;
         
         if(cur.used && dh[v] != dis) continue;
         if(!cur.used && d[v] != dis) continue;
         
         for(Edge e : adj.get(v)){
            int to = e.to;
            int len = e.len;
            if(to == n) continue;
            
            if(cur.used){
               if(dh[v] + len < dh[to]){
                  dh[to] = dh[v] + len;
                  pq.add(new State(dh[to],to,true));
               }
            } else {
               //check for not eating haybale
               if(d[v] + len < d[to]){
                  d[to] = d[v] + len;
                  pq.add(new State(d[to],to,false));
               }
               //check for eat haybale
               if(haybales[v] > 0){
                  if(dh[v] + len - haybales[v] < dh[to]){
                     dh[to] = dh[v] + len - haybales[v];
                     pq.add(new State(dh[to],to,true));
                  }
               }
            }
         }
      }
      
      for(int k = 1; k <= n; k++){
         if(haybales[k] > 0){
            if(d[k]*2 <= haybales[k]){
               for(int j = 1; j < n; j++){
                  System.out.println("1");
                  out.println("1");
               }
               out.close();
               System.exit(0);
            }
         }
      }
      
      
      for(int k = 1; k < n; k++){
         if(dh[k] <= d[k]){
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
      boolean used;
      
      public State(int a, int b, boolean c){
         dis = a;
         v = b;
         used = c;
      }
      
      public int compareTo(State s){
         return dis - s.dis;
      }
      public String toString(){
         return dis + " " + v + " " + used;
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