//make sure to make new file!
import java.io.*;
import java.util.*;

public class D618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n+1];
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         
         points[k] = new Point(x,y);
         
      }
      
      points[n] = points[0];
      
      if(n % 2 == 0 && check(points,n)){
         out.println("YES");
      } else {
         out.println("NO");
      }
      
   
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(Point[] points, int n){
      int n2 = n/2;
      for(int k = 0; k < n2; k++){
         int dx1 = points[k+1].x-points[k].x;
         int dy1 = points[k+1].y-points[k].y;
         int dx2 = points[k+n2].x-points[k+n2+1].x;
         int dy2 = points[k+n2].y-points[k+n2+1].y;
         
         if(dx1 != dx2 || dy1 != dy2) return false;
      }
      return true;
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
   }
   
      
}