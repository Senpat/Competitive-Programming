//make sure to make new file!
import java.io.*;
import java.util.*;

public class C1216{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long x1 = Long.parseLong(st.nextToken());
      long y1 = Long.parseLong(st.nextToken());
      long x2 = Long.parseLong(st.nextToken());
      long y2 = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long x3 = Long.parseLong(st.nextToken());
      long y3 = Long.parseLong(st.nextToken());
      long x4 = Long.parseLong(st.nextToken());
      long y4 = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long x5 = Long.parseLong(st.nextToken());
      long y5 = Long.parseLong(st.nextToken());
      long x6 = Long.parseLong(st.nextToken());
      long y6 = Long.parseLong(st.nextToken());
      
      
      if(area(x1,y1,x2,y2,x3,y3,x4,y4) + area(x1,y1,x2,y2,x5,y5,x6,y6) - area2(x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6) == Math.abs(x1-x2) * Math.abs(y1-y2)){
         out.println("NO");
      } else {
         out.println("YES");
      }
      
      
      
      
      
      out.close();
   }
   
   //area of 3,4 over white part
   public static long area2(long x1,long y1,long x2, long y2,long x3,long y3,long x4, long y4, long x5, long y5, long x6, long y6){
      x3 = Math.max(x3,x1);
      x4 = Math.min(x4,x2);
      x5 = Math.max(x5,x1);
      x6 = Math.min(x6,x2);
      
      y3 = Math.max(y3,y1);
      y4 = Math.min(y4,y2);
      y5 = Math.max(y5,y1);
      y6 = Math.min(y6,y2);
      
      if(x4 <= x3 || x6 <= x5) return 0;
      if(y4 <= y3 || y6 <= y5) return 0;
      
      //rSystem.out.println(x3 + " " + y3 + " " + x4 + " " + y4 + " " + x5 + " " + y5 + " " + x6 + " " + y6);
      return area(x3,y3,x4,y4,x5,y5,x6,y6);
   }
      
   public static long area(long x1,long y1,long x2, long y2,long x3,long y3,long x4, long y4){
      long maxx1 = Math.max(x1,x2);
      long minx1 = Math.min(x1,x2);
      long maxx2 = Math.max(x3,x4);
      long minx2 = Math.min(x3,x4);
      
      long maxy1 = Math.max(y1,y2);
      long miny1 = Math.min(y1,y2);
      long maxy2 = Math.max(y3,y4);
      long miny2 = Math.min(y3,y4);
      
      if(minx1 >= maxx2 || minx2 >= maxx1) return 0;
      if(miny1 >= maxy2 || miny2 >= maxy1) return 0;
      
      long x = Math.abs(Math.min(maxx1,maxx2) - Math.max(minx1,minx2));
      long y = Math.abs(Math.min(maxy1,maxy2) - Math.max(miny1,miny2));
      
      //System.out.println(x*y);
      return x*y;
   }
      
}