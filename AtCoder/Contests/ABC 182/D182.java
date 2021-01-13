//make sure to make new file!
import java.io.*;
import java.util.*;

public class D182{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long sum = 0L;
      long max = 0L;
      
      long answer = 0L;
      long sumsum = 0L;
      
      for(int k = 0; k < n; k++){
         sum += array[k];
         max = Math.max(max,sum);
         
         answer = Math.max(answer,sumsum+max);
         
         sumsum += sum;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}