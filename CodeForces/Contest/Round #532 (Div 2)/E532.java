//make sure to make new file!
import java.io.*;
import java.util.*;

public class E532{

   public static ArrayList<HashSet<Integer>> parent;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Edge[] edges = new Edge[n+1];
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>(m,Collections.reverseOrder());
      
      for(int k = 1; k <= m; k ++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         Edge e = new Edge(k,a,b,c);
         
         edges[k] = e;
         pq.add(e);
      }
      
      while(!pq.isEmpty()){
         Edge e = pq.poll();
         
         
      
      
      
      
      out.close();
   }
   
   public static HashSet<Integer> copy(HashSet<Integer> hset){
      HashSet<Integer> hset2 = new HashSet<Integer>();
      for(int i : hset){
         hset2.add(i);
      }
      return hset2;
   }
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static HashSet<Integer> find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }
   
   
   public static class Edge implements Comparable<Edge>{
      int id;
      int u;
      int v;
      int w;
      public Edge(int a, int b, int c, int d){
         id = a;
         u = b;
         v = c;
         w = d;
      }
      public int compareTo(Edge e){
         return w-e.w;
      }
   }
   
}