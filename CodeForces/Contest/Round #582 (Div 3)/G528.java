//make sure to make new file!
import java.io.*;
import java.util.*;

public class G528{

   public static int[] parent;
   public static int[] sizes;
   public static int MAX  = 200005;
   
   public static long answer = 0;
   public static long[] comb;
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      comb = new long[MAX];
      for(int k = 1; k < MAX; k++){
         comb[k] = 1L * k * (k-1) / 2;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>(MAX);
      for(int k = 0; k < MAX; k++){
         edges.add(new ArrayList<Edge>());
      }
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         edges.get(c).add(new Edge(a,b));
      }
      
      

      //instantiate dsu arrays
      //ONE INDEXED
      parent = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         parent[k] = k;
      }
      
      sizes = new int[n+1];                     //stores depth of every dsu
      Arrays.fill(sizes,1);
      
      long[] answers = new long[MAX];
      
      for(int k = 0; k < MAX; k++){
         for(Edge e : edges.get(k)){
            union(e.a,e.b);
         }
         answers[k] = answer;
      }
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         int i = Integer.parseInt(st.nextToken());
         out.print(answers[i] + " ");
      }
         
      
      out.close();
   }
   
   
   public static class Edge{
      int a;
      int b;
      public Edge(int c, int d){
         a = c;
         b = d;
      }
   }
      
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      
      answer -= comb[sizes[findu]];
      answer -= comb[sizes[findv]];
      
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
      
      
      
      answer += comb[sizes[findv]];
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }

   
      
}