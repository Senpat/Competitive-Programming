/*
TASK: moocast
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class moocast{
   
   public static int[] parents;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("moocast.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] array = new Point[n];
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         array[k] = new Point(a,b);
      
      }
      
      ArrayList<Edge> edges = new ArrayList<Edge>(n*n/2);
      
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            edges.add(new Edge(k,j,array[k].d(array[j])));
         }
      }
      
      Collections.sort(edges);
      
      parents = new int[n];
      
      for(int k = 0; k < n; k++) parents[k] = k;
      
      
      int max = 0;
      
      for(int k = 0; k < edges.size(); k++){
         Edge e = edges.get(k);
         if(find(e.a) != find(e.b)){
            union(e.a,e.b);
            max = Math.max(max,e.w);
         }
      }
      System.out.println(max);
      out.println(max);      
      
        
        
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
   
   public static class Point{
      int x,y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
      public int d(Point p){
         return (int)Math.pow(p.x-x,2) + (int)Math.pow(p.y-y,2);
      }
   }
      
}