//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Long[] array = new Long[n];
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(f.readLine());
         sum += array[k];
      }
      
      Arrays.sort(array);
      
      long min = sum;
      
      long a = 0L;
      long b = sum;
      
      for(int k = 0; k < n; k++){
         a += array[k];
         b -= array[k];
         min = Math.min(min,Math.abs(a-b));
      }
      
      out.println(min);
      
      
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}