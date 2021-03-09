//make sure to make new file!
import java.io.*;
import java.util.*;

public class datacenter{

   public static int THRESH = 75;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n];
      ArrayList<Long> xs = new ArrayList<Long>(n);
      ArrayList<Long> ys = new ArrayList<Long>(n);
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         points[k] = new Point(a,b);
         xs.add(a);
         ys.add(b);
      }
      Collections.sort(xs);
      Collections.sort(ys);
      Point average = new Point(xs.get(n/2),ys.get(n/2));          //actually more like the median
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
         public int compare(Integer a, Integer b){
            long d1 = distsq(average,points[(int)a]);
            long d2 = distsq(average,points[(int)b]);
            if(d1 == d2) return 0;
            if(d1 > d2) return 1;
            return -1;
         }
      });
      
      for(int k = 0; k < n; k++){
         pq.add(k);
      }
      
      long answer = Long.MAX_VALUE;
      int index = -1;
      for(int k = 0; k < Math.min(n,THRESH); k++){
         int i = pq.poll();
         long cur = 0;
         for(int j = 0; j < n; j++){
            cur += Math.max(Math.abs(points[i].x-points[j].x),Math.abs(points[i].y-points[j].y));
         }
         if(cur < answer){
            answer = cur;
            index = i;
         } else if(cur == answer && i < index){
            index = i;
         }
      }
      
      out.println(index+1);
      
      
      
      
      
      
      out.close();
   }
   
   public static long distsq(Point a, Point b){
      return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
   }
   
   public static class Point{
      long x;
      long y;
      public Point(long a, long b){
         x = a;
         y = b;
      }
   }
      
}