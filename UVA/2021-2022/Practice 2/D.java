//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{

   public static double e = 0.00000001;
   public static int ITER = 100;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n];
      for(int k = 0;  k< n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         points[k] = new Point(a,b);
      }
      
      //shoelace theorem
      long sum = 0L;
      for(int k = 0; k < n-1; k++){
         sum += (points[k].x*points[k+1].y) - (points[k+1].x*points[k].y);
      }
      sum += points[n-1].x*points[0].y - points[n-1].y*points[0].x;
      
      double area = Math.abs(sum)/2.0;
      double half = area/2.0;
      
      double curarea = 0.0;
      
      int edge = -1;                 //edge between points[k-1] and points[k] is the location of the answer
      double startarea = 0.0;
      
      for(int k = 2; k < n; k++){
         double prev = curarea;
         curarea += area(points[0],points[k-1],points[k]);
         if(Math.abs(curarea-half) <= e){
            out.println(points[k].x + " " +points[k].y);
            out.close();
            return;
         }
         
         if(curarea > half){
            startarea = prev;
            edge = k;
            break;
         }
      }
      
      
      //parametric equation
      long dx = points[edge].x-points[edge-1].x;
      long dy = points[edge].y-points[edge-1].y;
      
      double l = 0.0;
      double r = 1.0;
      
      double ansx = -1;
      double ansy = -1;
      
      for(int iter = 0; iter < ITER; iter++){
         double mid = (l+r)/2.0;
         
         ansx = points[edge-1].x+dx*mid;
         ansy = points[edge-1].y+dy*mid;
         double newarea = startarea + area(points[0],points[edge-1],ansx,ansy);
         
         if(Math.abs(half-newarea) <= e){
            break;
         }
         
         if(newarea > half){
            r = mid;
         } else {
            l = mid;
         }
      
      }
      
      out.println(ansx + " " + ansy);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static double area(Point a, Point b, double cx, double cy){
      return Math.abs(a.x*b.y - a.y*b.x + b.x*cy - b.y*cx + cx*a.y - cy*a.x)/2.0;
   }
   
   public static double area(Point a, Point b, Point c){
      return Math.abs(a.x*b.y - a.y*b.x + b.x*c.y - b.y*c.x + c.x*a.y - c.y*a.x)/2.0;
   }
   
   public static class Point{
      long x; 
      long y;
      public Point(long a, long b){
         x = a;
         y = b;
      
      }
   }
   
      
}