//make sure to make new file!
import java.io.*;
import java.util.*;

public class B770{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         long y = Long.parseLong(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long sum = 0;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
         }
         
         if((Math.abs(y-x) % 2) == (sum%2)){
            out.println("Alice");
         } else {
            out.println("Bob");
         }
          

      }
      
      
      
      
      out.close();
   }
   
      
}