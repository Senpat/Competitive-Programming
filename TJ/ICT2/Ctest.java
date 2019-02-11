//make sure to make new file!
import java.io.*;
import java.util.*;

public class Ctest{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(new FileWriter("Ctest.out"));
      
      int n = 5;
      int t = 5;
      
      out.println(n + " " + t);
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            out.print((int)(Math.random()*(n-1)));
         }
         out.println();
      }
      
      
      
      
      out.close();
   }
   
      
}