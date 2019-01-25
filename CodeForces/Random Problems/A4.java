//Watermelon
import java.io.*;
import java.util.*;

public class A4{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      if(n%2==0 && n != 2)
      out.print("YES");
      else
      out.print("NO");
      
      out.close();
   }
   
}