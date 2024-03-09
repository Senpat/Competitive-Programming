//make sure to make new file!
import java.io.*;
import java.util.*;
//slope trick practice
//bug: think about how x0 is updated based on old top and new values
public class arc123_d{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      //each point in the pq represents slope changing by 2 
      PriorityQueue<Long> pq = new PriorityQueue<Long>(10,Collections.reverseOrder());
      long offset = 0L;
      long x0 = 0L;              //value at x=0 (top of the pq
      
      if(array[0] >= 0L){
         pq.add(0L);
         x0 = array[0];
      } else {
         pq.add(array[0]);
         x0 = Math.abs(array[0]);
      }
      
      for(int k = 1; k < n; k++){
         if(array[k] > array[k-1]){
            //shift
            offset -= (array[k]-array[k-1]);
         }
         
         long oldtop = pq.peek();
         //add 0 and array[k]
         pq.add(offset);
         pq.add(array[k] + offset);
         
         pq.poll();
         
         if(oldtop > Math.max(offset,array[k]+offset)){
            x0 += 2L*(oldtop - Math.max(offset,array[k]+offset));
         }
         x0 += Math.abs(array[k]);
         
      }
      
      //out.println(pq.peek() - offset);
      out.println(x0);
      
      
      
      
      
      out.close();
   }
   
      
}