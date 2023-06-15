//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1623{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }
      
      long[] masksum = new long[1 << n];
      
      int msb = 0;
      for(int k = 1; k < (1 << n); k++){
         if(k >= (1L << (msb+1))) msb++;
         masksum[k] = array[msb] + masksum[k ^ (1 << msb)];
      }
      
      long answer = Long.MAX_VALUE;
      for(int k = 0; k < (1 << n); k++){
         answer = Math.min(answer,Math.abs(sum-2*masksum[k]));
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}