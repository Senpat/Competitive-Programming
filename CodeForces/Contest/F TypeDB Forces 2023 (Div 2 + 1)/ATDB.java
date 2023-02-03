//make sure to make new file!
import java.io.*;
import java.util.*;

public class ATDB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         if(n%2 == 0){
            out.println("1 " + (n/2));
         } else {
            out.println("-1");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}