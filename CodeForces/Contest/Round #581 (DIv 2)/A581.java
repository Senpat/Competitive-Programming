//make sure to make new file!
import java.io.*;
import java.util.*;

public class A581{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int n = (s.length())/2;
      
      if(s.length()%2 == 1 && s.substring(1).contains("1")){
         n++;
      }
      
      out.println(n);
      

      
      
      
      
      out.close();
   }
   
      
}