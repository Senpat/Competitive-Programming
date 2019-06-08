//make sure to make new file!
import java.io.*;
import java.util.*;

public class A65{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         String s = f.readLine();
      
         if(s.indexOf('8') != -1 && n-s.indexOf('8')>=11){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      
      
      out.close();
   }
   
      
}