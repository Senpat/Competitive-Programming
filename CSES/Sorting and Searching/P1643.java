//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1643{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long answer = array[0];
      long min = Math.min(array[0],0);
      long curpsum = array[0];
      for(int k = 1; k < n; k++){
         curpsum += array[k];
         
         answer = Math.max(answer,curpsum-min);
         
         min = Math.min(min,curpsum);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}