//make sure to make new file!
import java.io.*;
import java.util.*;

public class A609{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n%2 == 0){
         out.println((n+4) + " 4");
      } else {
         out.println((n+9) + " 9");
      } 
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}