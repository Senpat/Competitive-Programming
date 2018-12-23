//make sure to make new file!
import java.io.*;
import java.util.*;

public class flooding{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n1 = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= n1; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         Point[] parray = new Point[3];
         for(int c = 0; c < 3; c++){
            parray[c] = new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
         }
         
         int np = Integer.parseInt(f.readLine());
         
         int answer = 0;
         for(int k = 0; k < np; k++){
            st = new StringTokenizer(f.readLine());
            Point pt1 = new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
            Point pt2 = new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
            
            if(checkin(parray[0],parray[1],parray[2],pt1) != checkin(parray[0],parray[1],parray[2],pt2)){
               answer++;
            }
         }
         
         out.println("Scenario "+q+": "+answer);
      
      }
      
      out.close();
   }
   
   public static boolean checkin(Point p1, Point p2, Point p3, Point p){
      //System.out.println(Area(p1,p2,p3) + " " + Area(p,p2,p3) + " " + Area(p1,p,p3) + " " + Area(p1,p2,p));
      return Area(p1,p2,p3) == Area(p,p2,p3) + Area(p1,p,p3) + Area(p1,p2,p);
   }
   
   
   public static double Area(Point p1, Point p2, Point p3){
      return Math.abs((p1.x * (p2.y-p3.y) + p2.x * (p3.y-p1.y) + p3.x * (p1.y-p2.y))/2.0);
   }
   
   
   public static class Point{
      double x;
      double y;
      public Point(double a, double b){
         x = a;
         y = b;
      }
      
      public String toString(){
         return x + " " + y;
      }   }
   
}