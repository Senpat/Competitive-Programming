//Colorful Slimes
//Um episode 8
import java.io.*;
import java.util.*;

public class BG004{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      
      long[] array = new long[2*n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         array[k+n] = array[k];
      }
      
      long[] shifts = new long[n];
      for(int k = n; k < 2*n; k++){
         long curmin = Long.MAX_VALUE;
         for(int shift = 0; shift < n; shift++){
            curmin = Math.min(curmin,array[k-shift]);
            shifts[shift] += curmin;
         }
      }
      
      long answer = Long.MAX_VALUE;
      for(int k = 0; k < n; k++){
         long cur = shifts[k] + x*k;
         answer = Math.min(answer,cur);
      }
      
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}