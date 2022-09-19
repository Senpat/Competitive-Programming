//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSF{
   
   public static ArrayList<ArrayList<ArrayList<Integer>>> partitions;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         points[k] = new Point(a,b);
      }
      
      //arraylist of possible arrangements. each arrangement is arraylist of arraylist (containing points in each region)
      ArrayList<Integer> start = new ArrayList<Integer>();
      for(int k = 0; k < n; k++) start.add(k);
      
      partitions = new ArrayList<ArrayList<ArrayList<Integer>>>();
      getbf(new ArrayList<ArrayList<Integer>>(),start);
      
      
      double min = Double.MAX_VALUE;
      
      for(ArrayList<ArrayList<Integer>> partition : partitions){
         
         double dist = 0.0;
         for(ArrayList<Integer> cursec : partition){
            ArrayList<Point> curpoints = new ArrayList<Point>();
            for(int i : cursec){
               curpoints.add(points[i]);
            }
            
            ArrayList<Point> hull = convexhull(curpoints);
      
            
            for(int k = 0; k < hull.size(); k++){
               dist += dist(hull.get(k),hull.get((k+1)%hull.size()));
            }
         
         }   
         min = Math.min(min,dist);
      
      }
      
      out.println(min);
      
      out.close();
   }
   
   public static void getbf(ArrayList<ArrayList<Integer>> cur, ArrayList<Integer> remaining){
      if(remaining.size() == 0){
         partitions.add(cur);
         return;
      }
      
      for(int k = 0; k < (1 << remaining.size()); k++){
         ArrayList<Integer> add = new ArrayList<Integer>();
         ArrayList<Integer> newremain = new ArrayList<Integer>();
         
         for(int j = 0; j < remaining.size(); j++){
            if((k & (1 << j)) == 0){
               newremain.add(remaining.get(j));
            } else {
               add.add(remaining.get(j));
            }
         }
         
         if(add.size() < 3 || (remaining.size() < 3 && remaining.size() != 0)) continue;
         
         //copy
         ArrayList<ArrayList<Integer>> newcur = new ArrayList<ArrayList<Integer>>();
         for(int j = 0; j < cur.size(); j++){
            ArrayList<Integer> cursec = new ArrayList<Integer>();
            for(int h = 0; h < cur.get(j).size(); h++){
               cursec.add(cur.get(j).get(h));
            }
            newcur.add(cursec);
         }
         
         newcur.add(add);
         
         getbf(newcur,newremain);
      }
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
   public static int crossproduct(Point a, Point b, Point c){
      return (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
   }
   
   
   public static double dist(Point a, Point b){
      return Math.sqrt((double)((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y)));
   }
   
   
   
   
   public static class Point implements Comparable<Point>{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
      //sort by x coordinate from left to right
      //if same x coordinate, sort by y coordinate from down to up
      public int compareTo(Point p){
         if(x != p.x) 
            return x-p.x;
         return y-p.y;
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