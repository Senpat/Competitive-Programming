//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());

      if(n <= 10000000) out.println("YES");
      else if(n > 100000000) out.println("NO");
      else out.println("MAYBE");
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}