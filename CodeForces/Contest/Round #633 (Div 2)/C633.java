//make sure to make new file!
import java.io.*;
import java.util.*;

public class C633{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         long max = 0L;
         
         for(int k = 1; k < n; k++){
            if(array[k] >= array[k-1]) 
               continue;
            long dif = array[k-1]-array[k];
            
            
            array[k]=array[k-1];
            max = Math.max(max,biggestbit(dif));
         }
         
         out.println(max);
            
            
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static long biggestbit(long n) 
   { 
      if (n == 0) 
         return 0; 
   
      int msb = 0; 
      while (n != 0) { 
         n = n / 2; 
         msb++; 
      } 
   
      return msb; 
   } 
   
      
}