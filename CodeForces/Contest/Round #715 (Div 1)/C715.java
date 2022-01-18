//make sure to make new file!
import java.io.*;
import java.util.*;

public class C715{

   public static int[] parent;
   public static int[] sizes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
         
      Edge[] marked = new Edge[m];
      long xor = 0L;
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long w = Long.parseLong(st.nextToken());
         
         marked[k] = new Edge(a,b,w,-1); 
         xor ^= w;
      }
         
      if(n >= 500){
         out.println(0);
         out.close();
         return;
      }
      
      //n guaranteed < 500, can do O(N^2)
      //set one of the unmarked to xor, get size of mst
      
      long answer = Long.MAX_VALUE;
      
      
      long[][] edges = new long[n+1][n+1];
      for(int k = 0; k < m; k++){
         edges[marked[k].from][marked[k].to] = marked[k].w;
         edges[marked[k].to][marked[k].from] = marked[k].w;
      }
      
      ArrayList<Edge> edgessort = new ArrayList<Edge>();
      ArrayList<Edge> unmarked = new ArrayList<Edge>();
      for(int k = 0; k < m; k++){
         edgessort.add(marked[k]);
      }
      int edgei = 0;
      for(int k = 1; k <= n; k++){
         for(int j = k+1; j <= n; j++){
            if(edges[k][j] == 0){
               edgessort.add(new Edge(k,j,0L,edgei));
               unmarked.add(new Edge(k,j,0L,edgei));
               edgei++;
            }
         }
      }
      edgessort.add(new Edge(-1,-1,xor,-2));
      Collections.sort(edgessort);
      
      for(int k = 0; k < edgei; k++){
            //kth unmarked edge is set to xor
            
            
            //do mst
            //instantiate dsu arrays
            //ONE INDEXED
         parent = new int[n+1];
         
         for(int j = 1; j <= n; j++){
            parent[j] = j;
         }
         
         sizes = new int[n+1];                     //stores depth of every dsu
         Arrays.fill(sizes,1);
            
         int added = 0;
         int edgeindex = 0;
         long curmst = 0L;
            
         while(added < n-1){
            Edge e = edgessort.get(edgeindex);
            if(e.index == -2){
               e = unmarked.get(k);
               e.w = xor;
            } else if(e.index == k){
               edgeindex++;
               continue;
            }
               
            if(find(e.from) != find(e.to)){
               curmst += e.w;
               union(e.from,e.to);
               added++;
            }
               
               
            edgeindex++;
         }
         
         answer = Math.min(answer,curmst);
            
            
      }
      
      
      out.println(answer);
   
      
      
      
      
      
      out.close();
   }
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      if(sizes[findv] > sizes[findu]){
         sizes[findv] += sizes[findu];
         parent[findu] = findv;
      } else {
         sizes[findu] += sizes[findv];
         parent[findv] = findu;
      }
   }
   
   public static int find(int v){
      if(parent[v] == v) 
         return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }

   
   public static class Edge implements Comparable<Edge>{
      int from;
      int to;
      long w;
      int index;                    //only for unmarked edges, -1 if marked
                                    //-2 IF XOR EDGE
      public Edge(int a, int b, long c, int d){
         from = a;
         to = b;
         w = c;
         index = d;
      }
      public int compareTo(Edge e){
         if(w > e.w) 
            return 1;
         if(w < e.w) 
            return -1;
         return 0;
      }
   }
         
      
}