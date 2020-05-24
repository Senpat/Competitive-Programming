//make sure to make new file!
import java.io.*;
import java.util.*;

public class B165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long cur = 100;
      int i = 0;
      while(cur < n){
         i++;
         cur = cur+cur/100;
      }
      
      out.println(i);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}