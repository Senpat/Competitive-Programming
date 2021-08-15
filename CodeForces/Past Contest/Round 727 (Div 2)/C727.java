//make sure to make new file!
import java.io.*;
import java.util.*;

public class C727{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      
      Long[] array = new Long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(array);
      
      PriorityQueue<Long> pq = new PriorityQueue<Long>();         //store diffs > x
      int diffs = 0;
      
      for(int k = 0; k < n-1; k++){
         if(array[k+1] - array[k] > x){
            pq.add(array[k+1] - array[k]);
            diffs++;
         }
      }
      
      while(!pq.isEmpty() && m > 0){
         long diff = pq.poll();
         long need = (diff-1)/x;
         if(need > m){
            m = 0;
            break;
         } else {
            m -= need;
            diffs--;
         }
      }
      
      out.println(diffs+1);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}