//make sure to make new file!
import java.io.*;
import java.util.*;

public class W{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
      
      for(int k = 0; k < t; k++){
         st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         pq.add(new Pair(l,d));
         pq.add(new Pair(r+1,-d));
      }
      
      long cur = 0L;
      long max = 0L;
      
      while(!pq.isEmpty()){
         Pair p = pq.poll();
         
         cur += p.i;
         
         if(pq.isEmpty() || pq.peek().t != p.t){
            max = Math.max(max,cur);
         }
      }
      
      out.println(max);
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int t;
      long i;
      public Pair(int a, long b){
         t = a;
         i = b;
      }
      public int compareTo(Pair p){
         return t-p.t;
      }
   }
   
      
}