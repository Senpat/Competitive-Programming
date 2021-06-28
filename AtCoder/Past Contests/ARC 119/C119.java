//make sure to make new file!
import java.io.*;
import java.util.*;

public class C119{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         if(k%2 == 1) array[k] *= -1;
      }
      
      //find number of subarrays that sum to 0
      
      HashMap<Long,Long> hmap = new HashMap<Long,Long>();
      long sum = 0L;
      long answer = 0L;
      
      hmap.put(sum,1L);
      for(int k = 0; k < n; k++){
         sum += array[k];
         if(hmap.containsKey(sum)){
            answer += hmap.get(sum);
            hmap.put(sum,hmap.get(sum)+1L);
         } else {
            hmap.put(sum,1L);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}