/*
TASK: cowtour
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class cowtour{
   
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
      
      double[][] dist = new double[n][n];
      for(int k = 0; k < n; k++) Arrays.fill(dist[k],Double.MAX_VALUE);
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(adj[k][j] != -1.0) dist[k][j] = adj[k][j];
         }
         dist[k][k] = 0.0;
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            for(int h = 0; h < n; h++){
               if(dist[j][k] == Double.MAX_VALUE || dist[k][h] == Double.MAX_VALUE) continue;
               
               if(dist[j][k] + dist[k][h] < dist[j][h]){
                  dist[j][h] = dist[j][k] + dist[k][h];
               }     
            }
         }
      }
      
      double maxf = 0.0;
      double[] farthest = new double[n];
      for(int k = 0; k < n; k++){
         double max = 0.0;
         for(int j = 0; j < n; j++){
            if(dist[k][j] != Double.MAX_VALUE) max = Math.max(max,dist[k][j]);
         }
         farthest[k] = max;
         
         maxf = Math.max(maxf,farthest[k]);
      }
      
      double answer = Double.MAX_VALUE;
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            if(dist[k][j] == Double.MAX_VALUE){
               answer = Math.min(answer, farthest[k] + farthest[j] + dist(points[k],points[j]));
            }
         }
      }
      
      answer = Math.max(answer,maxf);
      
      out.println(String.format("%.6f",answer));
      
      out.close();
   }
      
   public static double dist(Point p1, Point p2){
      return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
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