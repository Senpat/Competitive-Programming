//make sure to make new file!
import java.io.*;
import java.util.*;

public class Baf{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int sum = 0;
      for(int k = 0; k < s.length(); k++){
         sum += Character.getNumericValue(s.charAt(k));
      }
      
      if(sum % 5 == 0){
         out.println("YES");
      } else {
         out.println("NO");
      }

      
      
      
      
      out.close();
   }
   
      
}