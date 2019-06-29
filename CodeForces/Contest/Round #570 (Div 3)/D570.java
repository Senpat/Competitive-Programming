//make sure to make new file!
import java.io.*;
import java.util.*;

public class D570{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         HashMap<Long,Long> hm = new HashMap<Long,Long>();
         HashMap<Long,Long> freq = new HashMap<Long,Long>();
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            long i = Long.parseLong(st.nextToken());
            if(hm.containsKey(i)){
               hm.put(i,hm.get(i)+1);
            } else {
               hm.put(i,1L);
            }
         }
         
         PriorityQueue<Long> pq = new PriorityQueue<Long>(10,Collections.reverseOrder());
         
         for(long i : hm.keySet()){
            long x = hm.get(i);
            
            if(freq.containsKey(x)){
               freq.put(x,freq.get(x)+1);
            } else {
               pq.add(x);
               freq.put(x,1L);
            }
         }
         
         long max = pq.peek();
         
         long answer = 0L;
         
         while(!pq.isEmpty() && max > 0){
            long i = pq.poll();
            answer += sum(Math.min(max,i),freq.get(i));
            max=Math.min(max,i)-freq.get(i);
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   public static long sum(long a, long b){
      b = Math.min(a,b);
      return (2*a-b+1)*b/2;
   }
   
      
}