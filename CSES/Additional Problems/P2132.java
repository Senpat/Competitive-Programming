//make sure to make new file!
import java.io.*;
import java.util.*;
//slope trick
public class P2132{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long answer = 0L;
      PriorityQueue<Long> pq = new PriorityQueue<Long>(10,Collections.reverseOrder());
      pq.add(array[0]);
      for(int k = 1; k < n; k++){
         if(array[k] >= pq.peek()){
            pq.add(array[k]);
         } else {
            answer += pq.poll()-array[k];
            pq.add(array[k]);
            pq.add(array[k]);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}