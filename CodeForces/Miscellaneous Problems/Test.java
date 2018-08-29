//make sure to make new file!
import java.io.*;
import java.util.*;

public class Test{
   
   public static void main(String[] args)throws IOException{
      PrintWriter out = new PrintWriter("test.out");
      
      out.println("100000 2");
      for(int k = 0; k < 100000; k++){
         out.print("0 ");
      }
      
      
      
      out.close();
   }
   
}