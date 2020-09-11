//make sure to make new file!
import java.io.*;
import java.util.*;

public class C382{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long[] fibo = new long[1000000];
      
      fibo[0] = 1L;
      fibo[1] = 2L;
      
      int i = 1;
      while(fibo[i] <= n){
         fibo[i+1] = fibo[i] + fibo[i-1];
         i++;
      }
      
      out.println(i-1);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}