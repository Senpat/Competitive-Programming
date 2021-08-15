//make sure to make new file!
import java.io.*;
import java.util.*;

public class rangebit{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}

class RangeBIT {
   final long[] value;
   final long[] weightedVal;

   RangeBIT(int treeTo) {
      value = new long[treeTo + 2];
      weightedVal = new long[treeTo + 2];
   }

   void updateHelper(int index, long delta) {
      long weightedDelta = ((long) index) * delta;
      for (int j = index; j < value.length; j += j & -j) {
         value[j] += delta;
         weightedVal[j] += weightedDelta;
      }
   }

   void update(int from, int to, long delta) {
      updateHelper(from, delta);
      updateHelper(to + 1, -delta);
   }

   long query(int to) {
      long res = 0;
      long weightedRes = 0;
      for (int j = to; j > 0; j -= j & -j) {
         res += value[j];
         weightedRes += weightedVal[j];
      }
      return (((long) (to + 1)) * res) - weightedRes;
   }

   long query(int from, int to) {
      if (to < from) {
         return 0;
      }
      return query(to) - query(from - 1);
   }
}
