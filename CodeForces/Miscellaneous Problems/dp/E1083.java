//make sure to make new file!
import java.io.*;
import java.util.*;
//basic convex hull trick, lines and queries are sorted
//blog https://codeforces.com/blog/entry/63823
public class E1083{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Rect[] rects = new Rect[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long x = Long.parseLong(st.nextToken());
         long y = Long.parseLong(st.nextToken());
         long a = Long.parseLong(st.nextToken());
         
         rects[k] = new Rect(x,y,a);
      }
      
      Arrays.sort(rects);
      long[] dp = new long[n];
      
      Deque<Line> dq = new LinkedList<Line>();
      dq.addFirst(new Line(0L,0L));
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         //query (you can remove lines because the queries are in sorted order)
         while(dq.size() >= 2){
            Line last = dq.pollLast();
            Line last2 = dq.pollLast();
            
            if(last.eval(rects[k].y) <= last2.eval(rects[k].y)){
               dq.addLast(last2);
            } else {
               dq.addLast(last2);
               dq.addLast(last);
               break;
            }
         }
         
         //use last
         dp[k] = rects[k].x*rects[k].y-rects[k].a + dq.peekLast().eval(rects[k].y);
         answer = Math.max(answer,dp[k]);
         
         //insert
         Line add = new Line(-1*rects[k].x,dp[k]);
         while(dq.size() >= 2){
            Line first = dq.pollFirst();
            Line first2 = dq.pollFirst();
            
            if(add.intersectionX(first) >= first.intersectionX(first2)){
               dq.addFirst(first2);
            } else {
               dq.addFirst(first2);
               dq.addFirst(first);
               break;
            }
         }
         dq.addFirst(add);
      }
      
      out.println(answer);
         
      
      
      
      
      
      
      out.close();
   }
   
   public static class Line{
      //y=mx+c
      long m;
      long c;
      public Line(long a, long b){
         m = a;
         c = b;
      }
      public long eval(long x){
         return m*x+c;
      }
      //returns x value of intersection (doesn't handle parallel)
      public double intersectionX(Line l){
         return ((double)(l.c-c))/((double)(m-l.m));
      }
      public String toString(){
         return m + " " + c;
      }
         
   }
   
   public static class Rect implements Comparable<Rect>{
      long x;
      long y;
      long a;
      public Rect(long q, long w, long e){
         x = q;
         y = w;
         a = e;
      }
      //sort by x
      public int compareTo(Rect r){
         if(x > r.x) 
            return 1;
         else if(x < r.x) 
            return -1;
         return 0;
      }
   }
   
   
   
      
}