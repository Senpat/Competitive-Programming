//make sure to make new file!
import java.io.*;
import java.util.*;

public class E660{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Segment[] segments = new Segment[n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         double a = Double.parseDouble(st.nextToken());
         double b = Double.parseDouble(st.nextToken());
         double c = Double.parseDouble(st.nextToken());
         
         segments[k] = new Segment(a,b,c);
      }
      
      PriorityQueue<Interval> pq = new PriorityQueue<Interval>();
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            if(segments[k].y == segments[j].y) continue;
            double yh = 0.0;
            double x1h = 0.0;
            double x2h = 0.0;
            double yl = 0.0;
            double x1l = 0.0;
            double x2l = 0.0;
            
            if(segments[k].y > segments[j].y){
               yh = segments[k].y;
               x1h = segments[k].x1;
               x2h = segments[k].x2;
               yl = segments[j].y;
               x1l = segments[j].x1;
               x2l = segments[j].x2;
            } else {
               yh = segments[j].y;
               x1h = segments[j].x1;
               x2h = segments[j].x2;
               yl = segments[k].y;
               x1l = segments[k].x1;
               x2l = segments[k].x2;
            }
            
            //calculate the intervals that make them intersect
            double i1 = (x2l-x1h)/(yh-yl);
            double i2 = (x1l-x2h)/(yh-yl);
            
            pq.add(new Interval(min(i1,i2),max(i1,i2)));
         }
      }
      
      //join intervals
      
      Stack<Interval> stk = new Stack<Interval>();
      
      stk.push(pq.poll());
      while(!pq.isEmpty()){
         Interval cur = pq.poll();
         Interval top = stk.peek();
         
         if(top.e <= cur.s){
            stk.push(cur);
         } else if(top.e < cur.e){
            top.e = cur.e;
            stk.pop();
            stk.push(top);
         }
      }
      
      ArrayList<Inteval> intervals = ArrayList<Interval>();
      while(!stk.isEmpty()){
         intervals.add(stk.pop());
      }
      
      Collections.reverse(intervals);
      
      if(check(0.0)){
         out.println(calc(0.0));
      } else {   
         
         double answer = Double.MAX_VALUE;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               //k is above j
               if(k == j || segments[k].y <= segments[j].y) continue;
               
               if(check((segments[j].x2-segments[k].x1)/(segments[k].y-segments[j].y),intervals){
                  
               
            
         out.println(answer);
      }
      
      
      
      
      
      out.close();
   }
   
   public static boolean calc(double x, Segment[] segments){
      double min = Double.MAX_VALUE;
      double max = Double.MIN_VALUE;
      
      for(int k = 0; k < segments.length; k++){
         double vala = segments[k].x1 + x*segments[k].y;
         double valb = segments[k].x2 + x*segments[k].y;
         
         min = Math.min(min,vala);
         min = Math.min(min,valb);
         max = Math.max(max,vala);
         max = Math.max(max,valb);
      }
      
      return max-min;
   }
   
   public static boolean check(double x, ArrayList<Interval> intervals);
      //find greatest interval with s < x
      
      int l = 0;
      int r = intervals.size()-1;
      int ans = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(intervals.get(mid).s < x){
            ans = mid;
            l = mid+1
         } else {
            r = mid-1;
         }
      }
      
      return ans == -1 || intervals.get(ans).e <= x;
   }
      
   
   
   public static class Interval implements Comparable<Interval>{
      double s;
      double e;
      public Interval(double a, double b){
         s = a;
         e = b;
      }
      public int compareTo(Interval i){
         //sort by start
         if(s > i.s) return 1;
         if(s < i.s) return -1;
         return 0;
      }
   
   public static class Segment{
      double y;
      double x1;
      double x2;
      public Segment(double a, double b, double c){
         y = a;
         x1 = b;
         x2 = c;
      }
   }
   
      
}