//make sure to make new file!
import java.io.*;
import java.util.*;

public class A124{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         long answer = (1 << n) -1;
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}