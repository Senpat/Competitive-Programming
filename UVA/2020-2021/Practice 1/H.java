//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Line[] lines = new Line[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         double x1 = Double.parseDouble(st.nextToken());
         double y1 = Double.parseDouble(st.nextToken());
         double x2 = Double.parseDouble(st.nextToken());
         double y2 = Double.parseDouble(st.nextToken());
         
         lines[k] = new Line(x1,y1,x2,y2);
      }
      
      double answer = 0.0;
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            for(int h = j+1; h < n; h++){
               if(lines[k].check(lines[j]) && lines[j].check(lines[h]) && lines[h].check(lines[k])){
                  Point a = lines[k].getintersection(lines[j]);
                  Point b = lines[j].getintersection(lines[h]);
                  Point c = lines[h].getintersection(lines[k]);
                  
                  answer = Math.max(answer,dist(a,b) + dist(b,c) + dist(c,a));
               }
            }
         }
      }
      
      
      if(answer <= 0.00000001) out.println("no triangle");
      else System.out.printf("%f\n",answer);
      
      
      
      
      
      
      out.close();
   }
   

   
   public static double dist(Point a, Point b){
      return Math.pow(Math.pow(a.x-b.x,2.0)+Math.pow(a.y-b.y,2.0),0.5);
      //return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
   }
   
      
   public static class Line{
      boolean vertical;
      double x;         //if it's vertical
      double m;
      double b;
      
      public Line(double x1, double y1, double x2, double y2){
         if(x1 == x2){
            x = x1;
            vertical = true;
         } else {
            //slope
            m = (y2-y1)/(x2-x1);
            b = y1-m*x1;
         }
      }
      
      //checks if intersection is possible
      public boolean check(Line l){
         if(vertical && l.vertical) return false;
         if(vertical || l.vertical) return true;
         return Math.abs(m - l.m) > 0.0000001;
      }
      
      public Point getintersection(Line l){
         if(vertical){
            return new Point(x,l.m*x+l.b);
         } else if(l.vertical){
            return new Point(l.x,m*l.x+b);
         } else {
            double retx = (b-l.b)/(l.m-m);
            double rety = m*retx+b;
            return new Point(retx,rety);
         }
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