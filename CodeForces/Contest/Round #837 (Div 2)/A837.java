//make sure to make new file!
import java.io.*;
import java.util.*;

public class A837{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int max = 0;
         int min = Integer.MAX_VALUE;
         int[] array = new int[n];
         for(int k= 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            min = Math.min(min,array[k]);
            max = Math.max(max,array[k]);
         }
         
         if(min == max){
            long answer = (long)n * (long)(n-1);
            out.println(answer);
         } else {
            long nummin = 0L;
            long nummax = 0L;
            for(int k = 0; k < n; k++){
               if(array[k] == min) nummin++;
               if(array[k] == max) nummax++;
               
            }
            
            long answer = nummin*nummax*2L;
            out.println(answer);
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}