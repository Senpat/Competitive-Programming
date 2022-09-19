//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSC{
   
   public static double e = 0.0000001;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         double a = Double.parseDouble(st.nextToken());
         double b = Double.parseDouble(st.nextToken());
         
         points[k] = new Point(a,b);
      }
      
      int min = Integer.MAX_VALUE;
      for(int k = 0; k < n; k++){
         for(int j = 1; j < n; j++){
            min = Math.min(min,getans(k,j,points));
            min = Math.min(min,getans(j,k,points));
         }
      }
      
      out.println(min);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int getans(int a, int b, Point[] points){
      int n = points.length;
      //try line from a to b
      ArrayList<Point> group1 = new ArrayList<Point>();           //greater than the line
      ArrayList<Point> group2 = new ArrayList<Point>();           //less than then line
      group1.add(points[a]);
      group2.add(points[b]);
      
      if(Math.abs(points[a].x-points[b].x) < e){
         for(int k = 0; k < n; k++){
            if(k == a || k == b) continue;
            if(points[k].x > points[a].x) group1.add(points[k]);
            else group2.add(points[k]);
         }
      } else {
         double slope = (points[a].y-points[b].y)/(points[a].x-points[b].x);
         for(int k = 0; k < n; k++){
            if(k == a || k == b) continue;
            double ythresh = slope*(points[k].x-points[a].x)+points[a].y;
            
            if(points[k].y > ythresh) group1.add(points[k]);
            else group2.add(points[k]);
         }
      }
      
      if(group1.size() != group2.size()){
         return Integer.MAX_VALUE;
      }
      
      Collections.sort(group1);
      Collections.sort(group2);
      
      ArrayList<Point> hull1 = convexhull(group1);
      ArrayList<Point> hull2 = convexhull(group2);
      
      return n-hull1.size()-hull2.size();
      
      
   }
   
   //takes in array of points sorted by x coordinate from left to right
   //outputs convex hull in counterclockwise order, starting from leftmost point.
   public static ArrayList<Point> convexhull(ArrayList<Point> points){
      
      ArrayList<Point> hull = new ArrayList<Point>();
      
      //lower
      Stack<Point> lower = new Stack<Point>();
      lower.add(points.get(0));
      
      for(int k = 1; k < points.size(); k++){
         //while the are at least 2 elements currently in stack and the the last two points + this point make a right turn (or collinear), pop
         while(lower.size() >= 2){
            Point last = lower.pop();
            Point last2 = lower.pop();
            
            if(crossproduct(last2,last,points.get(k)) <= 0){
               lower.push(last2);
            } else {
               lower.push(last2);
               lower.push(last);
               break;
            }
         }
         lower.push(points.get(k));
      }
      
      //upper hull
      Stack<Point> upper = new Stack<Point>();
      upper.add(points.get(points.size()-1));
      
      for(int k = points.size()-2; k >= 0; k--){
         //while the are at least 2 elements currently in stack and the the last two points + this point make a right turn (or collinear), pop
         while(upper.size() >= 2){
            Point last = upper.pop();
            Point last2 = upper.pop();
            
            if(crossproduct(last2,last,points.get(k)) <= 0){
               upper.push(last2);
            } else {
               upper.push(last2);
               upper.push(last);
               break;
            }
         }
         upper.add(points.get(k));
      }
      
      //add upper then lower then reverse
      ArrayList<Point> hullupper = new ArrayList<Point>();
      while(!upper.isEmpty()){
         hullupper.add(upper.pop());
      }
      
      ArrayList<Point> hulllower = new ArrayList<Point>();
      while(!lower.isEmpty()){
         hulllower.add(lower.pop());
      }
      
      Collections.reverse(hullupper);
      Collections.reverse(hulllower);
      
      for(Point p : hulllower) hull.add(p);
      for(int k = 1; k < hullupper.size()-1; k++) hull.add(hullupper.get(k));          //don't add first and last points
      
      return hull;
               
      
   }
   
   //positive if a->b->c is left turn (counter-clockwise)
   //negative if right turn (clockwise)
   //0 is a,b,c are collinear
   public static double crossproduct(Point a, Point b, Point c){
      return (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
   }
   
      
   
   
   
   
   
   
   public static class Point implements Comparable<Point>{
      double x;
      double y;
      public Point(double a, double b){
         x = a;
         y = b;
      }
      //sort by x coordinate from left to right
      //if same x coordinate, sort by y coordinate from down to up
      public int compareTo(Point p){
         if(x > p.x+e) return 1;
         if(x < p.x-e) return -1;
         if(y > p.y+e) return 1;
         if(y < p.y-e) return -1;
         return 0;
         /*
         if(x != p.x) 
            return x-p.x;
         return y-p.y;
         */
      }
      
   }
     
      
}