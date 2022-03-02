//make sure to make new file!
import java.io.*;
import java.util.*;

public class B103{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         long[] array = new long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long max = array[0];
         long cursum = 0;
         for(int k = 1; k < n; k++){
            max = Math.max((array[k]*100L+m-1)/m-cursum,max);
            cursum += array[k];
         }
         
         out.println(max-array[0]);

      }
      
      
      
      
      out.close();
   }
   
      
}