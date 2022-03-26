//make sure to make new file!
import java.io.*;
import java.util.*;

public class A769{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();
         
         if(n >= 3) out.println("NO");
         else if(n == 2 && array[0] == array[1]) out.println("NO");
         else out.println("YES");
      

      }
      
      
      
      
      out.close();
   }
   
      
}