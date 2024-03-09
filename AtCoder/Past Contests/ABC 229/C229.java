//make sure to make new file!
import java.io.*;
import java.util.*;

public class C229{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Cheese> pq = new PriorityQueue<Cheese>(10,Collections.reverseOrder());
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         long a = Long.parseLong(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         pq.add(new Cheese(a,b));
      }
      
      long answer = 0L;
      while(w > 0 && !pq.isEmpty()){
         Cheese c = pq.poll();
         if(c.g >= w){
            answer += c.d * w;
            w = 0;
         } else {
            answer += c.d * c.g;
            w -= c.g;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Cheese implements Comparable<Cheese>{
      long d;
      int g;
      public Cheese(long a, int b){
         d = a;
         g = b;
      }
      
      public int compareTo(Cheese c){
         if(d > c.d) return 1;
         if(d < c.d) return -1;
         return 0;
      }
   }
      
}