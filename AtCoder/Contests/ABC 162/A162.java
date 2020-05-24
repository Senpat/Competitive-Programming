//make sure to make new file!
import java.io.*;
import java.util.*;

public class A162{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      if(s.charAt(0) == '7' || s.charAt(1) == '7'|| s.charAt(2)=='7'){
         out.println("Yes");
      } else {
         out.println("No");
      }
      
      

      
      
      
      
      
      out.close();
   }
   
      
}