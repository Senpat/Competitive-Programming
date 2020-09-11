import java.io.*;
import java.util.*;
//Convex Hull Trick with sorted lines and queries (queries in increasing order and slope of lines in decreasing order)
//Solution to https://codeforces.com/contest/319/problem/C
//https://codeforces.com/blog/entry/63823

//getting lower hull
public class cht_sortedlinesandqueries{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] a = new long[n];
      long[] b = new long[n];
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(st1.nextToken());
         b[k] = Long.parseLong(st2.nextToken());
      }
      
      long[] dp = new long[n];
      
      dp[0] = b[0];
      
      Deque<Line> dq = new LinkedList<Line>();
      dq.addFirst(new Line(b[0],dp[0]));
      
      for(int k = 1; k < n; k++){
         //query
         while(dq.size() > 1){
            Line last = dq.pollLast();
            Line last2 = dq.pollLast();
            
            if(last.eval(a[k]-1) >= last2.eval(a[k]-1)){
               dq.addLast(last2);
            } else {
               dq.addLast(last2);
               dq.addLast(last);
               break;
            }
         }
        
         long cur = dq.peekLast().eval(a[k]-1);
         dp[k] = cur + b[k];
        
         //insert
         
         
         Line newline = new Line(b[k],dp[k]);
         while(dq.size() > 1){
            Line last = dq.pollFirst();
            Line last2 = dq.pollFirst();
            
            if(newline.intersectionX(last) <= last.intersectionX(last2)){
               dq.addFirst(last2);
            } else {
               dq.addFirst(last2);
               dq.addFirst(last);
               break;
            }
         
         }
         
         dq.addFirst(newline);
      }
      
      out.println(dp[n-1]);
      
      
      
      
      
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
}