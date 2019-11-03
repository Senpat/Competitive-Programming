//make sure to make new file!
import java.io.*;
import java.util.*;

public class A572{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      int ones = 0;
      int zeros = 0;
      
      for(int k = 0; k < n; k++){
         if(s.charAt(k) == '1') ones++;
         else zeros++;
      }
      
      if(ones != zeros){
         out.println(1);
         out.println(s);
      } else {
         out.println(2);
         out.println(s.substring(0,n-1) + " " + s.charAt(n-1));
      }
      

      
      
      
      
      out.close();
   }
   
      
}