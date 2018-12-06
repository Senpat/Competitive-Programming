/*
TASK: mootube
LANG: JAVA
*/
//semi-t
import java.io.*;
import java.util.*;

class mootube{

   public static int[] parent;
   public static int[] sizes;
   
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("mootube.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Edge[] edges = new Edge[n-1];
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
        
         edges[k] = new Edge(a,b,c);
      }
      
      Arrays.sort(edges);
      
      Query[] queries = new Query[m];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         queries[k] = new Query(a,b,k);
      }
      
      Arrays.sort(queries);
      
      parent = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         parent[k] = k;
      }
      
      sizes = new int[n+1];                     //stores depth of every dsu
      Arrays.fill(sizes,1);
      
      int index = 0;
      
      int[] answer = new int[m];
      for(int k = 0; k < m; k++){
         while(index < n-1 && edges[index].w >= queries[k].k){
            union(edges[index].a,edges[index].b);
            index++;
         }
         answer[queries[k].index] = sizes[find(queries[k].v)]-1;
      }
      
      for(int k = 0; k < m; k++){
         System.out.println(answer[k]);
         out.println(answer[k]);
      }
      
        
      out.close();
   }
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }
      
   public static class Edge implements Comparable<Edge>{
      int a,b,w;
      public Edge(int z, int x, int c){
         a = z;
         b = x;
         w = c;
      }
      public int compareTo(Edge e){
         return e.w-w; 
      }                             //sort backwards
   }
   
   public static class Query implements Comparable<Query>{
      int k,v,index;
      public Query(int a, int b,int c){
         k = a;
         v = b;
         index = c;
      }
      public int compareTo(Query q){
         return q.k-k;
      }
   }
}