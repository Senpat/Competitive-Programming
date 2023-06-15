//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1068{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      StringJoiner sj = new StringJoiner(" ");
      long i = n;
      sj.add("" + i);
      while(i != 1){
         if(i%2 == 1) i = i*3+1;
         else i >>= 1;
         sj.add("" + i);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}