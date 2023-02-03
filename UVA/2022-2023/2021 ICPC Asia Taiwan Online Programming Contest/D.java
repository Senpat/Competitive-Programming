//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      double n = Double.parseDouble(f.readLine());
      
      double answer = 1.0/(n-1.0) + (n-2.0)/(n-1.0) * 0.5;
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}