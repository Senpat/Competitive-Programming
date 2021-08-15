//make sure to make new file!
import java.io.*;
import java.util.*;

public class B730{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long sum = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
         }
         
         long mod = sum%(long)n;
         
         long answer = mod*((long)n-mod);
         out.println(answer);
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}