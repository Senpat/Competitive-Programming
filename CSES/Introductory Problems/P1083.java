//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1083{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n-1];
      long sum = 0L;
      for(int k = 0; k < n-1; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }
      
      long nl = (long)n;
      long answer = nl*(nl+1)/2 - sum;
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}