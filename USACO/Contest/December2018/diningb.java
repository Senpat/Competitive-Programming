/*
TASK: dining
LANG: JAVA
*/
//in-contest implementation, doesnt work
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
      
      int[] d = new int[n+1];
      Arrays.fill(d,1000000000);
      d[n] = 0;
      int[] dh = new int[n+1];                           //already eaten haybale
      Arrays.fill(dh,1000000000);
      //dh[n] =0;
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      
      pq.add(new State(0,n,false));
      
      boolean[] seen = new boolean[n+1];
      boolean[] seenh = new boolean[n+1];
      
      while(!pq.isEmpty()){
         State cur = pq.poll();
         int dis = cur.dis;
         int v = cur.v;
         
         if(cur.used){
            if(seenh[v]) continue;
            seenh[v] = true;
         } else {
            if(seen[v]) continue;
            seen[v] = true;
         }
         
         for(Edge e : adj.get(v)){
            int to = e.to;
            int len = e.len;
            //if(to == n) continue;
            
            if(cur.used){
               if(dis + len < dh[to]){
                  dh[to] = dis + len;
                  pq.add(new State(dh[to],to,true));
               }
            } else {
               //check for not eating haybale
               if(dis + len < d[to]){
                  d[to] = dis + len;
                  pq.add(new State(d[to],to,false));
               }
               //check for eat haybale
               if(haybales[v] > 0){
                  if(dis + len - haybales[v] < dh[to]){
                     dh[to] = dis + len - haybales[v];
                     pq.add(new State(dh[to],to,true));
                  }
               }
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