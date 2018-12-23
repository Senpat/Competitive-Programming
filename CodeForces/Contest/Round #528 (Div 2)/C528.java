//make sure to make new file!
import java.io.*;
import java.util.*;

public class C528{
   
   public static HashSet<Point> answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int x3 = Integer.parseInt(st.nextToken());
      int y3 = Integer.parseInt(st.nextToken());
      
      //hashset between 1 and 2
      
      HashSet<Point> hs1 = new HashSet<Point>();
      
      for(int k = Math.min(x1,x2); k <= Math.max(x1,x2); k++){
         for(int j = Math.min(y1,y2); j <= Math.max(y1,y2); j++){
            hs1.add(new Point(k,j));
         }
      }
      
      //hs between 2 and 3
      
      HashSet<Point> hs2 = new HashSet<Point>();
      
      for(int k = Math.min(x2,x3); k <= Math.max(x2,x3); k++){
         for(int j = Math.min(y2,y3); j <= Math.max(y2,y3); j++){
            Point cur = new Point(k,j);
            
            if(hs1.contains(cur)) hs2.add(cur);
         }
      }
      
      //hs between 1 and 3
      
      HashSet<Point> hs3 = new HashSet<Point>();
      
      for(int k = Math.min(x1,x3); k <= Math.max(x1,x3); k++){
         for(int j = Math.min(y1,y3); j <= Math.max(y1,y3); j++){
            Point cur = new Point(k,j);
            
            if(hs2.contains(cur)) hs3.add(cur);
         }
      }
      
      //hs guaranteed to contain 1 point
      Point center;
      for(Point p : hs3){
         center = p;
      }
      
      answer = new HashSet<Point>();
      
      answer.add(new Point(x1,y1));
      answer.add(new Point(x2,y2));
      answer.add(new Point(x3,y3));
      
      out.println(answer.size());
      
      for(Point p : answer){
         out.println(p);
      }
      
      
      out.close();
   }
   
   public static void find(Point p, Point c){
      //find shortest distance and fill answer hs
      for(int k = Math.min(p.x,c.x); k <= Math.max(p.x,c.x); k++){
         for(int j = Math.min(p.y,c.y); j <= Math.max(p.y,c.y); j++){
            Point cur = new Point(k,j);
            answer.add(cur);
         }
      }
   }
   
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
      
      @Override
      public int hashCode(){
         return (x + " " + y).hashCode();
      }
      
      @Override
      public boolean equals(Object o){
         Point p = (Point)o;
         return x == p.x && y == p.y;
      }
      public String toString(){
         return x + " " + y;
      }
   }
   
}