//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{

   public static double PI  = 3.1415926536;
   public static double PI2 = 6.2831853072;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Circle[] circles = new Circle[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         double x = Double.parseDouble(st.nextToken());
         double y = Double.parseDouble(st.nextToken());
         double r = Double.parseDouble(st.nextToken());
         
         circles[k] = new Circle(x,y,r);
      }
      
      ArrayList<PriorityQueue<Interval>> intervals = new ArrayList<PriorityQueue<Interval>>(n);
      for(int k = 0; k < n; k++) intervals.add(new PriorityQueue<Interval>());
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j < k; j++){
            //c0 is covering c1
            Circle c0 = circles[k];
            Circle c1 = circles[j];
            double d = dist(c0.x,c0.y,c1.x,c1.y);
            //check if there are intersection points
            if(c0.r + c1.r <= d) continue;               //too far apart
            
            //check if c1 is inside c0
            if(d+c1.r <= c0.r){
               intervals.get(j).add(new Interval(0.0,PI2));
               continue;
            }
            
            //check if c0 is inside c1
            if(d+c0.r <= c1.r){
               continue;
            }
            
            //get intersection points
            double a = (c0.r*c0.r - c1.r*c1.r + d*d)/(2.0*d);
            double h = Math.sqrt(c0.r*c0.r-a*a);
            
            double p2x = c0.x + a*(c1.x - c0.x)/d;
            double p2y = c0.y + a*(c1.y - c0.y)/d;
            
            //first intersection point
            double a1x = p2x + h*(c1.y - c0.y)/d;
            double a1y = p2y - h*(c1.x - c0.x)/d;
            //second intersection point
            double a2x = p2x - h*(c1.y - c0.y)/d;
            double a2y = p2y + h*(c1.x - c0.x)/d;
            
            double theta1 = gettheta(a1x,a1y,c1.x,c1.y);
            double theta2 = gettheta(a2x,a2y,c1.x,c1.y);
            
            if(theta2 <= theta1){
               intervals.get(j).add(new Interval(theta2,theta1));
            } else {
               intervals.get(j).add(new Interval(0.0,theta1));
               intervals.get(j).add(new Interval(theta2,PI2));
            }
            
         }
      }
      
      double answer = 0.0;
      for(int k = 0; k < n; k++){
         //get size of intersection of the intervals in intervals.get(k)
         
         double curstart = -1.0;
         double curend = -1.0;
         double sum = 0.0;
         while(!intervals.get(k).isEmpty()){
            Interval i = intervals.get(k).poll();     
            if(curstart == -1.0){
               curstart = i.l;
               curend = i.r;
            } else {
               if(i.l > curend){
                  sum += curend-curstart;
                  curstart = i.l;
                  curend = i.r;
               } else {
                  curend = Math.max(curend,i.r);
               }
            }
         }
         
         if(curstart != -1.0){
            sum += curend-curstart;
         }
         
         double prop = 1.0-sum/PI2;
         answer += PI2*circles[k].r*prop;
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   public static double gettheta(double x, double y, double cx, double cy){
      double dy = y-cy;
      double dx = x-cx;
      
      double atan = Math.atan2(dy,dx);
      if(atan < 0) atan += PI2;
      
      return atan;
   }
   
   public static double dist(double x1, double y1, double x2, double y2){
      return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
   }
   
   public static class Interval implements Comparable<Interval>{
      double l;
      double r;
      public Interval(double a, double b){
         l = a;
         r = b;
      }
      public int compareTo(Interval i){
         //sort by l
         if(l < i.l) return -1;
         if(l > i.l) return 1;
         //sort by r
         if(r < i.r) return -1;
         if(r > i.r) return 1;
         return 0;
      }
   }
   
   public static class Circle{
      double x;
      double y;
      double r;
      public Circle(double a, double b, double c){
         x = a;
         y = b;
         r = c;
      }
   }
   
      
}