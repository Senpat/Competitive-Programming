//BerSU Ball
import java.io.*;
import java.util.*;

public class B489{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int b = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      PriorityQueue<Integer> bpq = new PriorityQueue<Integer>();
      
      for(int k = 0; k < b; k++) bpq.add(Integer.parseInt(st.nextToken()));
      
      int g = Integer.parseInt(f.readLine());
      
      st = new StringTokenizer(f.readLine());
      
      PriorityQueue<Integer> gpq = new PriorityQueue<Integer>();
      
      for(int k = 0; k < g; k++) gpq.add(Integer.parseInt(st.nextToken()));
      
      int answer = 0;
      
      while(!bpq.isEmpty() && !gpq.isEmpty()){
         if(Math.abs(bpq.peek() - gpq.peek()) <= 1){
            answer++;
            bpq.poll();
            gpq.poll();
         } else {
            if(bpq.peek()<gpq.peek()){
               bpq.poll();
            } else {
               gpq.poll();
            }
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
}