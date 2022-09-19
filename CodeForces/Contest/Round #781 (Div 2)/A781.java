//make sure to make new file!
import java.io.*;
import java.util.*;

public class A781{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         out.println((n-3) + " 1 1 1");
      

      }
      
      
      
      
      out.close();
   }
   
      
}