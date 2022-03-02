//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG14{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashSet<Long> pow2 = new HashSet<Long>();
      
      long i = 1L;
      
      while(i*i < 1000000005){
         pow2.add(i*i*2L);
         pow2.add(i*i*4L);
         i++;
      }
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long x = Long.parseLong(f.readLine());
         
         if(pow2.contains(x)){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}