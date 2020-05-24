//make sure to make new file!
import java.io.*;
import java.util.*;

public class DAF20{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int i = Integer.parseInt(s.substring(1));
      out.println(i%2);
      
      

      
      
      
      
      
      out.close();
   }
   
      
}