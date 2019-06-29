//make sure to make new file!
import java.io.*;
import java.util.*;

public class A570
{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      
      int n = Integer.parseInt(f.readLine());
      
      while(true){
         if(check(n)){
            out.println(n);
            break;
         } else {
            n++;
         }
      }
      

      
      
      
      
      out.close();
   }
   
   public static boolean check(int i){
      String s = Integer.toString(i);
      int sum = 0;
      for(int k = 0; k < s.length(); k++){
         sum += Character.getNumericValue(s.charAt(k));
      }
      return sum%4 == 0;
   }
      
}