//make sure to make new file!
import java.io.*;
import java.util.*;

public class CAF20{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = Integer.toBinaryString(n);
      while(s.length() < 6){
         s="0"+s;
      }
      String answer = ""+s.charAt(0)+s.charAt(5)+s.charAt(3)+s.charAt(2)+s.charAt(4)+s.charAt(1);
      out.println(Integer.parseInt(answer,2));
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}