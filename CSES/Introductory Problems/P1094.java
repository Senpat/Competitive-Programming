//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1094{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long max = array[0];
      long answer = 0L;
      for(int k = 1; k < n; k++){
         answer += Math.max(0,max-array[k]);
         max = Math.max(max,array[k]);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}