//Point Ordering
import java.io.*;
import java.util.*;

public class F601{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      //loop through all points, find max for left and right side
      
      PriorityQueue<Pair> leftpq = new PriorityQueue<Pair>(10,Collections.reverseOrder());
      PriorityQueue<Pair> rightpq = new PriorityQueue<Pair>(10,Collections.reverseOrder());
      
      for(int k = 3; k <= n; k++){
         out.println("2 1 2 " + k);
         out.flush();
         long i = Long.parseLong(f.readLine());
         if(i > 0){
            //ccw, right side
            out.println("1 1 2 " + k);
            out.flush();
            long a = Long.parseLong(f.readLine());
            rightpq.add(new Pair(k,a));
         } else {
            //cw, left side
            out.println("1 1 2 " + k);
            out.flush();
            long a = Long.parseLong(f.readLine());
            leftpq.add(new Pair(k,a));
         }
      }
      
      //quadrants with 1 at top and 2 at bot, mids at x axis
      //print 1 pq2 midleft pq3 2 pq4 midright pq1
      PriorityQueue<Pair> pq1 = new PriorityQueue<Pair>(10,Collections.reverseOrder());
      PriorityQueue<Pair> pq2 = new PriorityQueue<Pair>();
      PriorityQueue<Pair> pq3 = new PriorityQueue<Pair>(10,Collections.reverseOrder());
      PriorityQueue<Pair> pq4 = new PriorityQueue<Pair>();
      
      Pair midright = new Pair(-1,-1);
      Pair midleft = new Pair(-1,-1);
      
      if(!rightpq.isEmpty()){
         midright = rightpq.poll();
      }
      
      if(!leftpq.isEmpty()){
         midleft = leftpq.poll();
      }
      
      while(!rightpq.isEmpty()){
         Pair p = rightpq.poll();
         out.println("2 1 " + midright.i + " " + p.i);
         out.flush();
         long i = Long.parseLong(f.readLine());
         if(i > 0){
            //ccw, quadrant 1;
            pq1.add(p);
         } else {
            pq4.add(p);
         }
      }
      
      while(!leftpq.isEmpty()){
         Pair p = leftpq.poll();
         out.println("2 1 " + midleft.i + " " + p.i);
         out.flush();
         long i = Long.parseLong(f.readLine());
         if(i > 0){
            //ccw, quadrant 3;
            pq3.add(p);
         } else {
            pq2.add(p);
         }
      }    
      
      StringJoiner sj = new StringJoiner(" ");
      sj.add("0 1");
      if(midleft.i != -1){
         while(!pq2.isEmpty()){
            sj.add("" + pq2.poll().i);
         }
         sj.add("" + midleft.i);
         while(!pq3.isEmpty()){
            sj.add("" + pq3.poll().i);
         }
      }
      sj.add("2");
      if(midright.i != -1){
         while(!pq4.isEmpty()){
            sj.add("" + pq4.poll().i);
         }
         sj.add("" + midright.i);
         while(!pq1.isEmpty()){
            sj.add("" + pq1.poll().i);
         }
      }
            
      out.println(sj);
      
      
      
      
      
      out.close();
   }
   
   
   public static class Pair implements Comparable<Pair>{
      int i;
      long sort;
      
      public Pair(int a, long b){
         i = a;
         sort = b;
      }
      
      public int compareTo(Pair p){
         if(sort > p.sort) return 1;
         if(sort < p.sort) return -1;
         return 0;
      
         
      }
   }
   
      
}