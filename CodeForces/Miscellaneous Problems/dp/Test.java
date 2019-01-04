//make sure to make new file!
import java.io.*;
import java.util.*;

public class Test{
   
   public static void main(String[] args)throws IOException{
      PrintWriter out = new PrintWriter(System.out);
      
      out.println("100000");
      for(int k = 1; k <= 100000; k++){
         out.print(k + " ");
      }
      out.println();
      
      
      out.close();
   }
   
}