//Edge Deletion
//tutorial
import java.io.*;
import java.util.*;

public class D1076{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      
      for(int k = 1; k <= m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
         long w = Long.parseLong(st.nextToken());
         
         adj.get(a).add(new Edge(k,b,w));
         adj.get(b).add(new Edge(k,a,w));
         
      }
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
      
      boolean[] visited = new boolean[n];
      
      long[] dj = new long[n];
      Arrays.fill(dj,Long.MAX_VALUE);
      dj[0] = 0;
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      pq.add(new Edge(0,0,0L));
      
      while(!pq.isEmpty()){
         Edge cur = pq.poll();
         if(cur.w > dj[cur.node]) continue;
         for( Edge e : adj.get(cur.node)){
            if(cur.w + e.w < dj[e.node]){
               dj[e.node] = cur.w + e.w;
               pq.add(new Edge(e.index,e.node,dj[e.node]));
            }
         }
      }
      
      long[] dj2 = dj.clone();
      
      Arrays.fill(dj,Long.MAX_VALUE);
      
      pq = new PriorityQueue<Edge>();
      ArrayList<Integer> last = new ArrayList<Integer>();
      pq.add(new Edge(-1,0,0L));
      
      while(!pq.isEmpty()){
         Edge cur = pq.poll();
         if(cur.index != -1 && cur.w == dj2[cur.node] && last.size() < i){
            last.add(cur.index);
         }
         if(last.size() >= i) break;
         if(cur.w > dj[cur.node]) continue;
         for(Edge e : adj.get(cur.node)){
            if(cur.w + e.w < dj[e.node]){
               dj[e.node] = cur.w + e.w;
               pq.add(new Edge(e.index,e.node,dj[e.node]));
            }
         }
      }
      
      out.println(last.size());
      for(int in : last){
         out.print(in + " ");
      }
      
      
      out.close();
   }
   
   
   public static class Edge implements Comparable<Edge>{
      
      int node;
      long w;
      int index;
      public Edge(int x, int c,long v){
         index = x;
         node = c;
         w = v;
      }
      
      public int compareTo(Edge e){
         return Long.compare(w,e.w);
      }
      
   }
   
   
}