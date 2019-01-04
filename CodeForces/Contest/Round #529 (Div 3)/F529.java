//make sure to make new file!
import java.io.*;
import java.util.*;

public class F529{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] cost = new long[n+1];
      TreeSet<Cost> costs = new TreeSet<Cost>();
      st = new StringTokenizer(f.readLine());
      
      for(int k = 1; k <= n; k++){
         long i = Long.parseLong(st.nextToken());
         cost[k] = i;
         costs.add(new Cost(i,k));
      }
      
      ArrayList<ArrayList<Pair>> offers = new ArrayList<ArrayList<Pair>>(n+1);
      
      for(int k = 0; k <= n; k++){
         offers.add(new ArrayList<Pair>());
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         
         offers.get(a).add(new Pair(c,b));
         offers.get(b).add(new Pair(c,a));
      }
      
      boolean[] seen = new boolean[n+1];
      
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
      
      Cost first = costs.pollFirst();
      seen[first.index] = true;
      
      
      //add costs + offers
      for(int k = 1; k <= n; k++){
         if(seen[k]) continue;
         pq.add(new Edge(first.c + cost[k], first.index,k));
      }
      
      for(Pair p: offers.get(first.index)){
         pq.add(new Edge(p.w,first.index,p.v));
      }
      
      long answer = 0L;
      int count = 1;
      while(count < n && !pq.isEmpty()){
         Edge cur = pq.poll();
         
         if(seen[cur.a] && seen[cur.b]) continue;
         
         int v = -1;
         if(!seen[cur.b]) v = cur.b;
         else v = cur.a;                  //shouldn't happen
         
         seen[v] = true;
         
         answer+=cur.w;
         count++;
         
         Cost cos = costs.pollFirst();
         while(!costs.isEmpty() && seen[cos.index]){
            cos = costs.pollFirst();
         }
         
         if(cos != null && !seen[cos.index]) pq.add(new Edge(cost[v] + cos.c, v,cos.index));
         
         for(Pair p: offers.get(v)){
            if(seen[p.v]) continue;
            pq.add(new Edge(p.w,v,p.v));
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
   public static class Cost implements Comparable<Cost>{
      long c;
      int index;
      public Cost(long a, int b){
         c = a;
         index = b;
      }
      public int compareTo(Cost t){
         if(c == t.c) return index - t.index;
         if(c < t.c) return -1;
         if(c == t.c) return 0;
         return 1;
      }
      public String toString(){
         return c + " " + index;
      }
   }
   
   //weight,other vertex
   public static class Pair{
      long w;
      int v;
      public Pair(long a, int b){
         w = a;
         v = b;
      }
      public String toString(){
         return w + " " + v;
      }
   }
   
   public static class Edge implements Comparable<Edge>{
      long w;
      int a;
      int b;
      public Edge(long p, int o, int i){
         w = p;
         a = o;
         b = i;
      }
      public int compareTo(Edge e){
         if(w == e.w && a == e.a) return b - e.b;
         if(w == e.w) return a - e.a;
         if(w < e.w) return -1;
         if(w == e.w) return 0;
         return 1;
      }
      public String toString(){
         return w + " " + a + " " + b;
      }
   }
   
   
}