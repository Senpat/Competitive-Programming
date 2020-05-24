//make sure to make new file!
import java.io.*;
import java.util.*;

public class A168{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int i = n%10;
      
      if(i == 3){
         out.println("bon");
      } else if(i == 0 || i == 1 || i == 6 || i == 8){
         out.println("pon");
      } else {
         out.println("hon");
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}