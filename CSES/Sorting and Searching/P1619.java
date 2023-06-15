//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1619{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      PriorityQueue<Time> pq = new PriorityQueue<Time>();
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int start = Integer.parseInt(st.nextToken());
         int end = Integer.parseInt(st.nextToken());
         
         pq.add(new Time(start,true));
         pq.add(new Time(end,false));
      }
      
      int cur = 0;
      int max = 0;
      while(!pq.isEmpty()){
         Time time = pq.poll();
         
         if(time.start) cur++;
         else cur--;
         
         max = Math.max(max,cur);
      }
      
      out.println(max);
      
      
      
      
      
      
      out.close();
   }
   
   public static class Time implements Comparable<Time>{
      int time;
      boolean start;
      public Time(int a, boolean b){
         time = a;
         start = b;
      }
      public int compareTo(Time t){
         return time-t.time;
      }
         
   }
   
      
}