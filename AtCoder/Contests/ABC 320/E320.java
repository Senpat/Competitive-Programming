//make sure to make new file!
import java.io.*;
import java.util.*;

public class E320{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] t = new int[m];
      long[] w = new long[m];
      long[] s = new long[m];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         t[k] = Integer.parseInt(st.nextToken());
         w[k] = Long.parseLong(st.nextToken());
         s[k] = Long.parseLong(st.nextToken());
      }
      
      long[] answer = new long[n+1];
      
      TreeSet<Integer> tset = new TreeSet<Integer>();
      for(int k = 1; k <= n; k++){
         tset.add(k);
      }
      
      PriorityQueue<Return> pq = new PriorityQueue<Return>();
      
      for(int k = 0; k < m; k++){
         while(!pq.isEmpty() && pq.peek().t <= t[k]){
            tset.add(pq.poll().p);
         }
         
         if(!tset.isEmpty()){
            int p = tset.first();
            answer[p] += w[k];
            pq.add(new Return(t[k] + s[k],p));
            tset.remove(p);
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static class Return implements Comparable<Return>{
      long t;
      int p;
      
      public Return(long a, int b){
         t = a;
         p = b;
      }
      
      public int compareTo(Return r){
         if(t < r.t) return -1;
         if(t > r.t) return 1;
         return 0;
      }
   }
   
      
}