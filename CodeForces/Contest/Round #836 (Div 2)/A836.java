//make sure to make new file!
import java.io.*;
import java.util.*;

public class A836{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         String s = f.readLine();
         
         for(int k = s.length()-1; k >= 0; k--){
            s += s.charAt(k);
         }
         
         out.println(s);
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}