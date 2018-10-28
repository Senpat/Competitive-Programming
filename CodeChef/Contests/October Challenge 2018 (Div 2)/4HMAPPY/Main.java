//4 HMAPPY
import java.io.*;
import java.util.*;

public class Main{

   public static long[] a,b;
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      a = new long[n];
      b = new long[n];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++) a[k] = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++) b[k] = Integer.parseInt(st.nextToken());
      
      PriorityQueue<State> pq = new PriorityQueue<State>(n,Collections.reverseOrder());
      
      for(int k = 0; k < n; k++){
         pq.add(new State(k,a[k]*b[k]));
      }
      
      for(long k = 0; k <= m; k++){
         State cur = pq.remove();
         pq.add(new State(cur.ind,cur.candles-b[cur.ind]));
      }
      
      out.println(pq.remove().candles);
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int ind;
      long candles;
      
      public State(int a, long b){
         ind = a;
         candles = b;
      }
      
      public int compareTo(State s){
         if(candles > s.candles) return 1;
         if(candles < s.candles) return -1;
         if(b[ind] < b[s.ind]) return 1;
         if(b[ind] > b[s.ind]) return -1;
         return 0;
         //return (int)(b[ind]-b[s.ind]);
         //return candles - s.candles;
      }
   }
   
}