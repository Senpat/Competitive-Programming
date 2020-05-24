//make sure to make new file!
import java.io.*;
import java.util.*;

public class A167{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s1 = f.readLine();
      String s2 = f.readLine();
      
      if(s2.length() == s1.length()+1 && s2.substring(0,s2.length()-1).equals(s1)){
         out.println("Yes");
      } else {
         out.println("No");
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}