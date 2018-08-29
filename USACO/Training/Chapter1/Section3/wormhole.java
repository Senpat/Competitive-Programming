/*
USER: patrickgzhang
TASK: wormhole
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class wormhole{
   
   public static int count = 0;
   /*public static int maxx = 0;
   public static int maxy = 0,minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;*/
   public static ArrayList<Point> commonp,sorted;
   public static HashMap<Integer,Integer> maxes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<Point> points = new ArrayList<Point>();
      maxes = new HashMap<Integer,Integer>();
      
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         /*maxx = Math.max(a,maxx);
         maxy = Math.max(maxy,b);
         minx = Math.min(a,minx);
         miny = Math.min(miny,b);
         */
         if(maxes.containsKey(b)){
            maxes.put(b,Math.max(maxes.get(b),a));
         } 
         else {
            maxes.put(b,a);
         }
         points.add(new Point(a,b));
      }
      
      Collections.sort(points);//least to greatest
      System.out.println(points);
      sorted = new ArrayList<Point>(points);
      
      HashSet<Integer> hs = new HashSet<Integer>();
      commonp = new ArrayList<Point>();
      
      for(int k = 0; k < points.size(); k++){
         Point pt = points.get(k);
         
         if(!hs.contains(pt.y)){
            hs.add(pt.y);
         } 
         else {
            commonp.add(new Point(pt.x,pt.y));
         }
      }
      System.out.println(commonp);            
      HashMap<Point,Point> hash = new HashMap<Point,Point>();
      
      calculate(hash,points);
      
      System.out.println(count);
      out.println(count);
      out.close();
      
   }
   
   public static void calculate(HashMap<Point,Point> h, ArrayList<Point> a){
      if(a.isEmpty()){
         System.out.println(h);
         for(Point pt : commonp){
            System.out.println("\t" + pt);
            boolean bool = true;
            
            //Point n = getnext(pt);
            int fastx = get(h,pt).x;
            int fasty = get(h,pt).y;
            int orix = fastx;
            int oriy = fasty;
            long count1 = 0L;
            while(bool){
               System.out.println(fastx + " " + fasty);
               if(/*pt.x == fastx && pt.y == fasty || */(count1 > 6 && fastx == orix && fasty == oriy)){ //count1 should be >12
                  System.out.println("count");
                  count++;
                  bool = false;
                  return;
               } 
               Point nextp = getnext(new Point(fastx,fasty));
               if(nextp == null){
                  bool = false;
               } else if(fastx == h.get(nextp).x && fasty == h.get(nextp).y){
                  System.out.println("count1");
                  count++;
                  bool = false;
                  return;
               }/*else if(pt.x == h.get(getnext(new Point(fastx,fasty))).x && pt.y == h.get(getnext(new Point(fastx,fasty))).y){
                  count++;
                  bool = false;
                  return;
               }*/

               else {
                  //int temp = fastx;
                  fastx = h.get(nextp).x;
                  fasty = h.get(nextp).y;
               
               }
               count1++;
               
            }
            
         }
            
      
      } 
      else {
         Point start = a.remove(0);
         for(int k = 0; k < a.size(); k++){
            ArrayList<Point> another = new ArrayList<Point>();
            another.addAll(a);
            Point next = another.remove(k);
            HashMap<Point,Point> h1 = new HashMap<Point,Point>();
            h1.putAll(h);
            h1.put(start,next);
            h1.put(next,start);
            calculate(h1,another);
         }
      }
   }
   
   public static Point getnext(Point p){
      Point t = null;
      //System.out.println(points);
      for(int k = 0; k < sorted.size(); k++){
         t = sorted.get(k);
         if(t.x > p.x && t.y == p.y){
            return t;
         }
      }
      return null;
   }
   
   public static boolean contains(HashMap<Point,Point> h, Point p){
      for(Point t : h.keySet()){
         if(t.equals(p)){
            return true;
         }
      }
      
      return false;
   }

   public static Point get(HashMap<Point,Point> h, Point p){
      for(Point t : h.keySet()){
         if(t.equals(p)){
            return h.get(t);
         }
      }
      
      return null;
   }
   
   public static void printh(HashMap<Point,Point> h){
      for(int k = 0; k < h.size(); k++){
      }
   }
   
   static class Point implements Comparable<Point>{
      int x;
      int y;
      
      public Point(int a, int b){
         x = a;
         y = b;
      }  
      
      public String toString(){
         return x + " " + y;
      }
      
      public boolean equals(Point p){
         return x == p.x && y == p.y;
      }
      
      public int compareTo(Point p){
         return x-p.x;
      }
   }
   
   
   
}