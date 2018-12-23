/*
TASK: fencedin
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class fencedin{
   
   public static int A,B,n,m,N;
   public static int[] parents,ranks;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("fencedin.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      if(n==0){
         out.println(m);
         out.close();
         System.exit(0);
      }
      if(m==0){
         out.println(n);
         out.close();
         System.exit(0);
      }
      
      int N = (n+1)*(m+1);
      
      int[] vert = new int[n+2];
      int[] hori = new int[m+2];
      vert[n+1] = A;
      hori[m+1] = B;
      
      
      for(int k = 1; k <= n; k++){
         vert[k] = Integer.parseInt(f.readLine());
      }
      for(int k = 1; k <= m; k++){
         hori[k] = Integer.parseInt(f.readLine());
      }
      Arrays.sort(vert);
      Arrays.sort(hori);
      
      
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
      ArrayList<Edge> edges = new ArrayList<Edge>(N*2);
      
      
      for(int k = 0; k <= n; k++){
         for(int j = 0; j <= m; j++){
            int curind = getindex(k,j);
            if(k > 0){
               int weight = hori[j+1]-hori[j];
               //pq.add(new Edge(curind,curind-1,weight));
               edges.add(new Edge(curind,curind-1,weight));
            }
            if(j > 0){
               int weight = vert[k+1]-vert[k];
               //pq.add(new Edge(curind,curind-n-1,weight));
               edges.add(new Edge(curind,curind-n-1,weight));
            }
         }
      }
      
      Collections.sort(edges);
      
      parents = new int[N];
      for(int k = 0; k < parents.length; k++){
         parents[k] = k;
      }
      ranks = new int[parents.length];
      
      
      long answer = 0L;
      int count = 0;
      /*while(count < N-1 && !pq.isEmpty()){
         Edge cur = pq.poll();
         if(find(cur.a) != find(cur.b)){
            answer+=(long)cur.w;
            union(cur.a,cur.b);
            count++;
         }
      }*/
      
      for(int k = 0; k < edges.size() && count < N-1; k++){
         Edge cur = edges.get(k);
         if(find(cur.a) != find(cur.b)){
            answer+=(long)cur.w;
            union(cur.a,cur.b);
            count++;
         }
      }
      
      System.out.println(answer);
      out.println(answer);
  
  
      out.close();
   }
   
   public static int find(int v){
      if(parents[v] == v) return v;
      else {
         parents[v] = find(parents[v]);
         return parents[v];
      }
   }
   
   public static void union(int u, int v){
      int findu = find(u);
      int findv = find(v);
      
      if(ranks[findu] < ranks[findv]){
         parents[findu] = findv;
      } else if(ranks[findv] < ranks[findu]){
         parents[findv] = findu;
      } else {
         parents[findv] = findu;
         ranks[findu]++;
      }
      
   }
   
   public static int getindex(int v, int h){
      return v + (n+1)*h;
   }
   
   public static class Edge implements Comparable<Edge>{
      int w;
      int a,b;
      public Edge(int z,int x,int c){
         a = z;
         b = x;
         w = c;
      }
      
      public int compareTo(Edge e){
         return w-e.w;
      }
   }
      
      
}