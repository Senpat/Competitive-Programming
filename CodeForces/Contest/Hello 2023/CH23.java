//make sure to make new file!
import java.io.*;
import java.util.*;

public class CH23{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken())-1;
      
         st = new StringTokenizer(f.readLine());
         
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         int answer = 0;
         
         //part to the right needs to have + prefix sum
         PriorityQueue<Long> pq = new PriorityQueue<Long>();
         long sum = 0L;
         for(int k = m+1; k < n; k++){
            sum += array[k];
            pq.add(array[k]);
            
            if(sum < 0){
               answer++;
               long i = pq.poll();
               sum += -2*i;
            }
         }
         
         //part to the left needs - sum
         if(m != 0){
            pq = new PriorityQueue<Long>(10,Collections.reverseOrder());
            sum = 0L;
            for(int k = m; k >= 1; k--){
               sum += array[k];
               pq.add(array[k]);
            
               if(sum > 0){
                  answer++;
                  long i = pq.poll();
                  sum -= 2*i;
               }
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}