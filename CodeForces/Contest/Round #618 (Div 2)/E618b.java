//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve with convex hull
public class E618b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n+1];
      Point[] points = new Point[n+1];
      long sum = 0L;
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
         points[k] = new Point(k,sum);
      }
      
      Stack<Point> stk = new Stack<Point>();
      stk.add(new Point(0,0L));
      
      for(int k = 1; k <= n; k++){
         while(stk.size() >= 2){
            Point last = stk.pop();
            Point last2 = stk.pop();
            
            if(crossproduct(last2,last,points[k]) <= 0){
               stk.push(last2);
            } else {
               stk.push(last2);
               stk.push(last);
               break;
            }
         }
         stk.push(points[k]);
      }
      
      ArrayList<Point> hull = new ArrayList<Point>();
      while(!stk.isEmpty()){
         hull.add(stk.pop());
      }
      
      Collections.reverse(hull);
      
      ArrayList<Double> answer = new ArrayList<Double>();
      
      for(int k = 1; k < hull.size(); k++){
         double d = (double)(hull.get(k).x-hull.get(k-1).x)/(double)(hull.get(k).i-hull.get(k-1).i);
         for(int j = hull.get(k-1).i; j < hull.get(k).i; j++){
            answer.add(d);
         }
      }
      
      for(int k = 0; k < answer.size(); k++){
         out.println(answer.get(k));
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   //positive if a->b->c is left turn (counter-clockwise)
   //negative if right turn (clockwise)
   //0 is a,b,c are collinear
   public static long crossproduct(Point a, Point b, Point c){
      return (long)(b.i-a.i)*(c.x-a.x) - (b.x-a.x)*(long)(c.i-a.i);
   }
   
   
   public static class Point{
      int i;
      long x;
      
      public Point(int a, long b){
         i = a;
         x = b;
      }
   
   }
}