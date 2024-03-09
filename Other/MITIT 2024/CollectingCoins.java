//make sure to make new file!
import java.io.*;
import java.util.*;

public class CollectingCoins{
   
   public static int n;
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         adj.get(a).add(new Edge(b,c,d));
         adj.get(b).add(new Edge(a,c,d));
      }
      
      //binary search on starting value
      
      long l = 0L;
      long r = 100000000000000L;
      long ans = r;
      
      while(l <= r){
         long mid = l + (r-l)/2L;
         
         if(check(mid)){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      out.println(ans);
      
      
      
      
      out.close();
   }
   
   public static boolean check(long x){
   
      //max[v] stores the max coins you can have when reaching v
      long[] max = new long[n+1];
      Arrays.fill(max,-1L);
      
      max[1] = x;
      
      PriorityQueue<Node> pq = new PriorityQueue<Node>(10,Collections.reverseOrder());
      pq.add(new Node(1,x));
      
      while(!pq.isEmpty()){
         Node node = pq.poll();
         int v = node.v;
         long d = node.d;
         
         if(v == n) return true;
         if(max[v] != d) continue;
         
         for(Edge e : adj.get(v)){
            if(d >= e.c){
               if(e.c < e.r) return true;
               
               long newd = d-e.c+e.r;
               if(newd > max[e.to]){
                  max[e.to] = newd;
                  pq.add(new Node(e.to,newd));
               }
            }
            
         }
         
      }
      
      return false;
      
   }
   
   
   public static class Node implements Comparable<Node>{
      int v;
      long d;
      public Node(int a, long b){
         v = a;
         d = b;
      }
      public int compareTo(Node node){
         if(d < node.d) return -1;
         if(d > node.d) return 1;
         return 0;
      }
   }
   
   public static class Edge{
      int to;
      long c;
      long r;
      public Edge(int a, long b, long cc){
         to = a;
         c = b;
         r = cc;
      }
   }
   
      
}