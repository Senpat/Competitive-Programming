//make sure to make new file!
import java.io.*;
import java.util.*;

public class A122{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         if(n%7 == 0){
            out.println(n);
            continue;
         }
         
         n = n-(n%10);
         
         int m7 = n%7;
         
         n += 7-m7;
         
         out.println(n);
      

      }
      
      
      
      
      out.close();
   }
   
      
}