//make sure to make new file!
import java.io.*;
import java.util.*;

public class D340{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] a = new long[n+1];
      long[] b = new long[n+1];
      int[] x = new int[n+1];
      
      for(int k = 1; k <= n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         a[k] = Long.parseLong(st.nextToken());
         b[k] = Long.parseLong(st.nextToken());
         x[k] = Integer.parseInt(st.nextToken());
      }
      
      long[] dist = new long[n+1];
      Arrays.fill(dist,Long.MAX_VALUE);
      
      dist[1] = 0L;
      PriorityQueue<State> pq = new PriorityQueue<State>();
      pq.add(new State(1,0L));
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(s.d != dist[s.v]) continue;
         if(s.v == n) break;
         
         //a
         if(dist[s.v+1] > s.d + a[s.v]){
            dist[s.v+1] = s.d + a[s.v];
            pq.add(new State(s.v+1,dist[s.v+1]));
         }
         //b
         if(dist[x[s.v]] > s.d + b[s.v]){
            dist[x[s.v]] = s.d + b[s.v];
            pq.add(new State(x[s.v],dist[x[s.v]]));
         }
      }
      
      out.println(dist[n]);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;
      long d;
      public State(int a, long b){
         v = a;
         d = b;
      }
      public int compareTo(State s){
         if(d > s.d) return 1;
         if(d < s.d) return -1;
         return 0;
      }
   }
}