//make sure to make new file!
import java.io.*;
import java.util.*;

public class B577{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      long sum = 0;
      long last = 0;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
         last = Math.max(last,array[k]);
      }
      sum -= last;
      if(sum < last || (sum-last)%2 == 1){
         out.println("NO");
      } else {
         out.println("YES");
      }

      
      
      
      
      out.close();
   }
   
      
}