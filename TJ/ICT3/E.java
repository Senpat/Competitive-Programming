//make sure to make new file!
import java.io.*;
import java.util.*;

public class E{
   
   public static int[] parents;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      parents = new int[n+1];
      
      for(int k = 0; k <= n; k++) parents[k] = k;

      

      Edge[] edges = new Edge[Math.max(m,1)];
      
      int sum = 0;
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         edges[k] = new Edge(a,b,w);
         sum += w;
      }
      
      if(m <= 0){
         out.println(sum);
         out.close();
         System.exit(0);
      }
      
      Arrays.sort(edges);
      
      
      int newsum = 0;
      for(int k = 0; k < m; k++){
         Edge e = edges[k];
         if(find(e.a) != find(e.b)){
            union(e.a,e.b);
            newsum += e.w;
         }
      }
      
      out.println(sum-newsum);
      out.close();
   }
   
   
   public static int find(int v){
      if(parents[v] == v) return v;
      else {
         parents[v] = find(parents[v]);
         return parents[v];
      }
   }
   
   public static void union(int v, int u){
      parents[find(v)] = find(u);
   }
   
   
   public static class Edge implements Comparable<Edge>{
      int a,b,w;
      public Edge(int z, int x, int c){
         a = z;
         b = x;
         w = c;
      }
      public int compareTo(Edge e){
         return w-e.w;
      }
   }

   
   
}