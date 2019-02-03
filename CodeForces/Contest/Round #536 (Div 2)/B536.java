//make sure to make new file!
import java.io.*;
import java.util.*;

public class B536{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] remain = new long[n+1];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         remain[k] = Integer.parseInt(st.nextToken());
      }
      
      //Pair[] c = new Pair[n+1];
      long[] c1 = new long[n+1];
      PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         long i = Long.parseLong(st.nextToken());
         c1[k] = i;
         //c[k] = new Pair(i,k);
         pq.add(new Pair(i,k));
      }
      //c[0] = new Pair(-1,-1);
      //Arrays.sort(c);
      
      
      for(int k = 0; k < m; k++){
         int t;
         long d;
      
         st = new StringTokenizer(f.readLine());
      
         t = Integer.parseInt(st.nextToken());
         d = Long.parseLong(st.nextToken());
      
         long cost = 0;
      
         if(remain[t] >= d){
            remain[t] -= d;
            cost = d*c1[t];
         } else {
         
            long need = d-remain[t];
            cost = remain[t]*c1[t];
            remain[t] = 0;
            int index = 1;
            while(need > 0 && index <= n && !pq.isEmpty()){
               int i = pq.peek().second;
               if(remain[i] >= need){
                  cost += need*c1[i];
                  remain[i]-=need;
                  need = 0;
                  
               } else {
                  cost += remain[i]*c1[i];
                  need-=remain[i];
                  remain[i] = 0;
               }
               if(remain[i]==0) pq.poll();
               index++;
            }
         
            if(need != 0){
               cost = 0;
            }
         
         
         }
      
         out.println(cost);
      }
   
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      long first;
      int second;
      public Pair(long a, int b){
         first = a;
         second = b;
      }
      public int compareTo(Pair p){
         if(first > p.first) 
            return 1;
         if(first < p.first) 
            return -1;
         return second - p.second;
      }
   }
      
      
}