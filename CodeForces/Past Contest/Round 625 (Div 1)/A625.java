//make sure to make new file!
import java.io.*;
import java.util.*;

public class A625{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      
      HashMap<Long,Long> hmap = new HashMap<Long,Long>();
      
      long max = 0L;
      
      for(int k = 0; k < n; k++){
         long key = array[k]-(long)k;
         if(hmap.containsKey(key)){
         hmap.put(key,hmap.get(key)+array[k]);
         } else {
            hmap.put(key,array[k]);
         }
         
         max = Math.max(max,(hmap.get(key)));
      }
      
      out.println(max);
      

      
      
      
      
      
      out.close();
   }
   
      
}