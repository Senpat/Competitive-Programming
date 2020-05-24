//make sure to make new file!
import java.io.*;
import java.util.*;

public class DM13{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
         
         long i = n;
         long first = 0L;
         int count = 0;
         while(i > 0){
            first += 1L << count;
            i >>= 1;
            count++;
         }
         
         first >>= 1L;
         
         out.println(first + " " + (n^first));
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}