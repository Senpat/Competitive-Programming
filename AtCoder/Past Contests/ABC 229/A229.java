//make sure to make new file!
import java.io.*;
import java.util.*;

public class A229{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String r1 = f.readLine();
      String r2 = f.readLine();
      
      if((r1.equals("#.") && r2.equals(".#")) || (r2.equals("#.") && r1.equals(".#"))){
         out.println("No");
      } else {
         out.println("Yes");
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}