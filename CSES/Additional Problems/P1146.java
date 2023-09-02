//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1146{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      int P = 50;
      
      long answer = 0L;
      for(int p = 1; p <= P; p++){
         long i = (1L << p);
         
         long comp = n/i;
         answer += comp * (i/2L);
         
         long rem = n%i;
         answer += Math.max(0,rem-i/2L+1);
      }
      
      out.println(answer);
         
      
      
      
      
      
      
      
      out.close();
   }
   
      
}