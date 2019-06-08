//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class D66{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      
      long sum = 0L;
      
      PriorityQueue<Long> pq = new PriorityQueue<Long>();
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
         if(k < n-1) pq.add(sum);
      }
      
      long answer = m*sum;
      for(int k = 0; k < m-1; k++){
         answer -= pq.poll();
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}