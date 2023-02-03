//make sure to make new file!
import java.io.*;
import java.util.*;

public class AGB22{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long[] array = new long[n];
         st = new StringTokenizer(f.readLine());
         PriorityQueue<Long> pq = new PriorityQueue<Long>();
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            pq.add(array[k]);
         }
         
         long[] b = new long[m];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < m; k++){
            b[k] = Long.parseLong(st.nextToken());
         }
         
         for(int k = 0; k < m; k++){
            pq.poll();
            pq.add(b[k]);
         }
         
         long sum = 0L;
         while(!pq.isEmpty()){
            sum += pq.poll();
         }
         
         out.println(sum);
      

      }
      
      
      
      
      out.close();
   }
   
      
}