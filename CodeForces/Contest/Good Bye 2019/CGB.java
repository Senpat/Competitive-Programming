//make sure to make new file!
import java.io.*;
import java.util.*;

public class CGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long[] array = new long[n];
         long sum = 0L;
         long bor = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
            bor ^= array[k];
         }
         
         long firstnum = bor;
         sum += firstnum;
         
         long secondnum = sum;
         
         out.println("2");
         out.println(firstnum + " " + secondnum);
         
         
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}