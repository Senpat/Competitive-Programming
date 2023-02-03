//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int n = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
         
         
      ArrayList<Point> points = new ArrayList<Point>();
      HashSet<Point> seen = new HashSet<Point>();
      
      double xmid = 0.0;
      double ymid = 0.0;
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         double a = Double.parseDouble(st.nextToken());
         double b = Double.parseDouble(st.nextToken());
         
         Point p = new Point(a,b);
         points.add(p);
         
         xmid += a;
         ymid += b;
      }
      
      xmid /= n;
      ymid /= n;
      Point pmid = new Point(xmid,ymid);
      
      Collections.sort(points);
      
      ArrayList<Point> hull = convexhull(points);
      
      double totalarea = triarea(pmid,hull.get(0),hull.get(n-1));
      for(int k = 1; k < n; k++){
         totalarea += triarea(pmid,hull.get(k-1),hull.get(k));
      }
      
      for(int k = 1; k < n; k++){
         totalarea += dist(hull.get(k-1),hull.get(k))*d*g;
         if(k < n-1){
            totalarea += getround(hull.get(k-1),hull.get(k),hull.get(k+1))*d*d*g*g;
         }
      }
      
      
      totalarea += dist(hull.get(0),hull.get(n-1))*d*g;
      totalarea += getround(hull.get(n-2),hull.get(n-1),hull.get(0))*d*d*g*g;
      totalarea += getround(hull.get(n-1),hull.get(0),hull.get(1))*d*d*g*g;
      
      out.println(totalarea);
      
      
      out.close();
   }
   
   
   public static Point zero = new Point(0.0,0.0);
   //get round part of a - b - c (around b)
   public static double getround(Point a, Point b, Point c){
      Point line1 = new Point(a.x-b.x,a.y-b.y);
      Point line2 = new Point(c.x-b.x,c.y-b.y);
      double theta = Math.acos(dot(line1,line2)/(dist(line1,zero)*dist(line2,zero)));
            
      double prop = (Math.PI - theta)/(2.0*Math.PI);
            
      return prop*Math.PI;
   }
   
   public static double dot(Point p1, Point p2){
      return p1.x*p2.x + p1.y*p2.y;
   }
   
   public static double dist(Point p1, Point p2){
      return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
   }
   
   //get area of triangle given 3 points
   public static double triarea(Point p1, Point p2, Point p3){
      return 0.5 * Math.abs(((p2.x-p1.x) * (p3.y-p1.y) - (p3.x-p1.x) * (p2.y-p1.y)));
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
         if(x != p.x) 
            if(x < p.x) 
               return -1;
            else 
               return 1;
         if(y < p.y) 
            return -1;
         if(y > p.y) 
            return 1;
         return 0;
      }
      
      public int hashCode(){
         return toString().hashCode();
      }
      
      public boolean equals(Object o){
         Point p = (Point)o;
         return x==p.x && y==p.y;
      }
      
      public String toString(){
         return "" + x + " " + y;
      }
   }
   
   
   
      
}