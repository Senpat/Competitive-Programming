//make sure to make new file!
import java.io.*;
import java.util.*;

public class D522{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      double a = Double.parseDouble(st.nextToken());
      double b = Double.parseDouble(st.nextToken());
      double c = Double.parseDouble(st.nextToken());                   //ax + by == -c, x = (-by-c)/a, y = (-ax-c)/b
      
      st = new StringTokenizer(f.readLine());
      
      double x1 = Double.parseDouble(st.nextToken());
      double y1 = Double.parseDouble(st.nextToken());
      double x2 = Double.parseDouble(st.nextToken());
      double y2 = Double.parseDouble(st.nextToken());
      
      if(a == 0 || b == 0){
         out.println(Math.abs(x1-x2) + Math.abs(y1-y2));
         out.close();
         System.exit(0);
      }
      
      double startx,starty,endx,endy;
      //find starting point on diagonal
      if(a*x1 + b*y1 + c == 0){
         startx = x1;
         starty = y1;
      } else {
         double cury = (-1.0*a*x1 - c)/b;
         if(Math.abs(cury - y2) < Math.abs(y1-y2)){
            startx = x1;
            starty = cury;
         } else {
            double curx = (-1.0*b*y1 - c)/a;
            startx = curx;
            starty = y1;
         }
      }
      
      //find ending point
      if(a*x2 + b*y2 + c == 0){
         endx = x2;
         endy = y2;
      } else {
         double cury = (-1.0*a*x2 - c)/b;
         if(Math.abs(cury - y2) < Math.abs(y1-y2)){
            endx = x2;
            endy = cury;
         } else {
            double curx = (-1.0*b*y2 - c)/a;
            endx = curx;
            endy = y2;
         }
      }
      
      double answer = 0.0;
      
      answer+=(Math.abs(startx-x1) + Math.abs(starty-y1));
      answer+=Math.sqrt(Math.pow(Math.abs(startx-endx),2) + Math.pow(Math.abs(starty-endy),2));
      answer+=(Math.abs(endx-x2) + Math.abs(endy-y2));
      
      out.println(Math.min(answer,Math.abs(x1-x2) + Math.abs(y1-y2)));
      
      out.close();
   }
}