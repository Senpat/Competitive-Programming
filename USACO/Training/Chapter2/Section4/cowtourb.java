/*
TASK: cowtour
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class cowtourb{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         double a = Double.parseDouble(st.nextToken());
         double b = Double.parseDouble(st.nextToken());
         
         points[k] = new Point(a,b);
      }
      
      double[][] adj = new double[n][n];
      for(int k = 0; k < n; k++) Arrays.fill(adj[k],-1.0);
      
      for(int k = 0; k < n; k++){   
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            if(s.charAt(j) == '1'){
               adj[k][j] = dist(points[k],points[j]);
            }
         }
      }
      
      double answer = Double.MAX_VALUE;
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            if(adj[k][j] != -1.0) continue;
            
            adj[k][j] = dist(points[k],points[j]);
            adj[j][k] = dist(points[k],points[j]);
            
            answer = Math.min(answer,getdiameter(n,adj));
            
            adj[k][j] = -1.0;
            adj[j][k] = -1.0;     
         }
      }
      
      out.println(String.format("%.6f",answer));
       
      out.close();
   }
   
   public static double getdiameter(int n, double[][] adj){
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      double[] dist1 = new double[n];
      Arrays.fill(dist1,Double.MAX_VALUE);
      dist1[0] = 0.0;
      pq.add(new State(0,0.0));
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(dist1[s.v] != s.w) continue;
         
         for(int nei = 0; nei < n; nei++){
            if(adj[s.v][nei] == -1.0) continue;
            if(dist1[nei] > dist1[s.v] + adj[s.v][nei]){
               dist1[nei] = dist1[s.v] + adj[s.v][nei];
               pq.add(new State(nei,dist1[nei]));
            }
         
         }
      }
      
      //get max
      int farthest = 0;
      for(int k = 1; k < n; k++){
         if(dist1[k] > dist1[farthest]){
            farthest = k;
         }
      }
      
      if(dist1[farthest] == Double.MAX_VALUE) return Double.MAX_VALUE;
      
      double[] dist2 = new double[n];
      Arrays.fill(dist2,Double.MAX_VALUE);
      dist2[farthest] = 0.0;
      pq.add(new State(farthest,0.0));
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(dist2[s.v] != s.w) continue;
         
         for(int nei = 0; nei < n; nei++){
            if(adj[s.v][nei] == -1.0) continue;
            if(dist2[nei] > dist2[s.v] + adj[s.v][nei]){
               dist2[nei] = dist2[s.v] + adj[s.v][nei];
               pq.add(new State(nei,dist2[nei]));
            }
         
         }
      }
      
      double max = 0.0;
      for(int k = 0; k < n; k++){
         max = Math.max(max,dist2[k]);
      }
      
      return max;
      
      
   }
   
   public static double dist(Point p1, Point p2){
      return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
   }
   
   public static class State implements Comparable<State>{
      int v;
      double w;
      public State(int a, double b){
         v = a;
         w = b;
      }
      
      public int compareTo(State s){
         if(w < s.w) return -1;
         if(w > s.w) return 1;
         return 0;
      }
   }
   
   public static class Point{
      double x;
      double y;
      public Point(double a, double b){
         x = a;
         y = b;
      }
   }
      
}