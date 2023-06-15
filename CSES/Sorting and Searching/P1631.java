//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1631{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      long sum = 0L;
      long max = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
         max = Math.max(max,array[k]);
      }
      
      // do a "rotation". first player gets biggest while second player gets 2nd biggest. Both players
      // do the next smallest one. The 2nd biggest is guaranteed to be availabe when the first player finishes
      // since 2nd biggest <= 1st biggest. The pattern persists until the second player gets to the biggest one. 
      // The only time the biggest one is not available is when max >= sum of the rest. In this case, the answer is 
      // 2*max. In other times, the answer is just the sum since there are no conflicts.
      if(max >= sum-max){
         out.println(2*max);
      } else {
         out.println(sum);
      }
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}