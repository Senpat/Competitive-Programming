//make sure to make new file!
//AC, strings intead of hashing
import java.io.*;
import java.util.*;

public class C528b{
   
   public static HashSet<String> answer;
   
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
      
      String s1 = x1 + " " + y1;
      String s2 = x2 + " " + y2;
      String s3 = x3 + " " + y3;
      
      //hashset between 1 and 2
      
      HashSet<String> hs1 = new HashSet<String>();
      
      for(int k = Math.min(x1,x2); k <= Math.max(x1,x2); k++){
         for(int j = Math.min(y1,y2); j <= Math.max(y1,y2); j++){
            Point cur = new Point(k,j);
            hs1.add(cur.toString());
         }
      }
      
      //hs between 2 and 3
      
      HashSet<String> hs2 = new HashSet<String>();
      
      for(int k = Math.min(x2,x3); k <= Math.max(x2,x3); k++){
         for(int j = Math.min(y2,y3); j <= Math.max(y2,y3); j++){
            Point cur = new Point(k,j);
            
            if(hs1.contains(cur.toString())) hs2.add(cur.toString());
         }
      }
      
      //hs between 1 and 3
      
      HashSet<String> hs3 = new HashSet<String>();
      
      for(int k = Math.min(x1,x3); k <= Math.max(x1,x3); k++){
         for(int j = Math.min(y1,y3); j <= Math.max(y1,y3); j++){
            Point cur = new Point(k,j);
            
            if(hs2.contains(cur.toString())) hs3.add(cur.toString());
         }
      }
      
      //hs guaranteed to contain 1 point
      String center = "";
      for(String p : hs3){
         center = p;
      }
      
      answer = new HashSet<String>();
      
      answer.add(s1);
      answer.add(s2);
      answer.add(s3);
      
      find(s1,center);
      find(s2,center);
      find(s3,center);
      
      out.println(answer.size());
      
      for(String p : answer){
         out.println(p);
      }
      
      
      
      out.close();
   }
   
   public static int minx,miny,maxx,maxy;
   
   public static void find(String p, String c){
      //find shortest distance and fill answer hs
      StringTokenizer st1 = new StringTokenizer(p);
      int x1 = Integer.parseInt(st1.nextToken());
      int y1 = Integer.parseInt(st1.nextToken());
      st1 = new StringTokenizer(c);
      int x2 = Integer.parseInt(st1.nextToken());
      int y2 = Integer.parseInt(st1.nextToken());
      
      minx =  Math.min(x1,x2);
      maxx = Math.max(x1,x2);
      miny =  Math.min(y1,y2);
      maxy = Math.max(y1,y2);
      
      if((x2 == minx && y2 == miny) || (x2 == maxx && y2 == maxy)){
         for(int k = minx; k <= maxx; k++){
            Point cur = new Point(k,miny);
            answer.add(cur.toString());
         }
         for(int k = miny; k <= maxy; k++){
            Point cur = new Point(maxx,k);
            answer.add(cur.toString());
         }
      } else {
         for(int k = minx; k <= maxx; k++){
            Point cur = new Point(k,miny);
            answer.add(cur.toString());
         }
         for(int k = miny; k <= maxy; k++){
            Point cur = new Point(minx,k);
            answer.add(cur.toString());
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
      public int hashCode(){
         return x*1001+y;
      }
      public boolean equals(Point p){
         return x == p.x && y == p.y;
      }
      public String toString(){
         return x + " " + y;
      }
   }
   
}