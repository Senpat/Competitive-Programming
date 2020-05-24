//make sure to make new file!
import java.io.*;
import java.util.*;

public class B168{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      if(s.length() <= n){
         out.println(s);
      } else {
         out.println(s.substring(0,n) + "...");
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}