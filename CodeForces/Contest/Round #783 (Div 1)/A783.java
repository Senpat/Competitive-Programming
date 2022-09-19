//make sure to make new file!
import java.io.*;
import java.util.*;

public class A783{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long min = Long.MAX_VALUE;
      //z is where the zero is
      for(int z = 0; z < n; z++){
         long answer = 0L;
         long prev = 0L;
         for(int k = z-1; k >= 0; k--){
            long add = prev/array[k]+1;
            answer += add;
            prev = add*array[k];
         }
         
         prev = 0L;
         for(int k = z+1; k < n; k++){
            long add = prev/array[k]+1;
            answer += add;
            prev = add*array[k];
         }
         
         min = Math.min(min,answer);
      }
      
      out.println(min);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}