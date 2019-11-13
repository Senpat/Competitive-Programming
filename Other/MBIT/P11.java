//make sure to make new file!
import java.io.*;
import java.util.*;

public class P11{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      
      out.println(8);
      out.println(6);
      
      
      
      
      out.close();
   }
   
   public static double getDist(double a1, double a2, double b1, double b2, double c1, double c2, double d1, double d2, double t){
      return Math.sqrt(a2*a2*t*t + 2*a2*b2*t + b2*b2 - 2*a1*a2*t*t - 2*b2*a1*t - 2*b1*a2*t - 2*b1*b2 + a1*a1*t*t + 2*a1*b1*t + b1*b1
      + c2*c2*t*t + 2*c2*d2*t + d2*d2 - 2*c2*c1*t*t - 2*c1*d2*t - 2*d1*c2*t - 2*d1*d2 + c1*c1*t*t + 2*c1*d1*t + d1*d1);
   }
   
      
}
class Point{
   public double x;
   public double y;
   
   public Point(double x, double y){
      this.x = x;
      this.y = y;
   }
   
   public double dist(Point p){
      return Math.sqrt((p.x - x)*(p.x-x) + (p.y-y)*(p.y-y));
   }
}

