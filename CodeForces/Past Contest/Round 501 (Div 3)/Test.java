//Dque
import java.io.*;
import java.util.*;
public class Test{
   public static void main(String[] args)throws IOException{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
      out.println("1000 1000");
      for(int k = 0; k < 1000; k++){
         for(int j = 0; j < 1000; j++){
            out.print("*");
         }
         out.println();
      }
      out.close();
   }
}