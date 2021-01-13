//make sure to make new file!
import java.io.*;
import java.util.*;

public class craters{
   
   public static double EPS = 0.0000000000000001;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 5000;
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<Point> points = new ArrayList<Point>();
      
      //double dtheta = 0.000628318530718;
      //double dtheta = 0.000837758040957;
      //double dtheta = 0.0012566370614359;
      double dtheta = 2.0*Math.PI/(double)N;
      for(int c = 0; c < n; c++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         double x = Double.parseDouble(st.nextToken());
         double y = Double.parseDouble(st.nextToken());
         
         double r = Double.parseDouble(st.nextToken()) + 10.0;
         
         double angle = 0.0;
         for(int k = 0; k < N; k++,angle += dtheta){
            points.add(new Point(x + r*Math.cos(angle),y + r*Math.sin(angle)));
         }
      }
   
      Collections.sort(points);/*
      for(int k = 0; k < 10; k++){
         out.println(points.get(k));
      }*/
      ArrayList<Point> answer = convexhull(points);
   
      double peri = 0.0;
      for(int k = 0; k < answer.size()-1; k++){
         peri += distance(answer.get(k),answer.get(k+1));
      }
      peri += distance(answer.get(0),answer.get(answer.size()-1));
      
      out.println(peri);
     
      
      out.close();
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
   
   public static double distance(Point a, Point b){
      return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
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
      /*public int compareTo(Point p){
         if(Math.abs(x-p.x) < EPS){
            if(x < p.x) return -1;
            else return 1;
         } else {
            if(Math.abs(y-p.y) < EPS) return 0;
            else if(y < p.y) return -1;
            else return 1;
         }
      }*/
      
      public int compareTo(Point p){
         if(x < p.x) return -1;
         else if(x > p.x) return 1;
         if(y < p.y) return -1;
         else if(y > p.y) return 1;
         return 0;
      }
      
      public int hashCode(){
         return toString().hashCode();
      }
      
      public boolean equals(Object o){
         Point p = (Point)o;
         return Math.abs(x-p.x) < EPS && Math.abs(y-p.y) < EPS;
      }
      
      public String toString(){
         return "" + x + " " + y;
      }
   }
   
   
   
      
}