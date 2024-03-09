//make sure to make new file!
import java.io.*;
import java.util.*;

public class C338{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] q = new long[n];
      for(int k = 0; k < n; k++){
         q[k] = Long.parseLong(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      long[] a = new long[n];
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      long[] b = new long[n];
      for(int k = 0; k < n; k++){
         b[k] = Long.parseLong(st.nextToken());
      }
      
      int N = 1000005;
      int max = 0;
      for(int k = 0; k <= N; k++){
         long[] cur = new long[n];
         boolean fail = false;
         
         for(int j = 0; j < n; j++){
            cur[j] = q[j] - (long)k * a[j];
            if(cur[j] < 0) fail = true;
         }
         
         if(fail) break;
         long min = Long.MAX_VALUE;
         for(int j = 0; j < n; j++){
            if(b[j] == 0L) continue;
            min = Math.min(min,cur[j] / b[j]);
         }
         
         max = Math.max(max,k+(int)min);
      }
      
      out.println(max);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}