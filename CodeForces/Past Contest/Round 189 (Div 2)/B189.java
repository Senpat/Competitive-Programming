//make sure to make new file!
import java.io.*;
import java.util.*;

public class B189{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<Point> points = new ArrayList<Point>();
      
      ArrayList<Point> pointsx = new ArrayList<Point>();
      ArrayList<Point> pointsy = new ArrayList<Point>();
      
      Comparator<Point> X = new Comparator<Point>(){
         public int compareTo(Point a, Point b){
            return a.x-b.x;
         }
      }
      
      Comparator<Point> Y = new Comparator<Point>(){
         public int compareTo(Point a, Point b){
            retur a.y-b.y;
         }
         
      }
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken()){
         if(i == 1){
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            points.add(new Point(x,y));
            
            pointsx.add(new Point(x,y));
            pointsy.add(new Point(x,y));
            
            Collections.sort(pointsx,X);
            Collections.sort(pointsy.Y);
         } else {
            //i == 2;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
         
         
            if(
      
      
      
      
      
      
      out.close();
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