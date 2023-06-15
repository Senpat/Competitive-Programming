//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1074{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      Long[] array = new Long[n];
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }
      
      Arrays.sort(array);
      
      long cur = sum - array[0] * n;
      long min = cur;
      
      for(int k = 1; k < n; k++){
         long diff = array[k]-array[k-1];
         cur += diff*k;
         cur -= diff*(n-k);
         min = Math.min(min,cur);
      }
      
      out.println(min);
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}