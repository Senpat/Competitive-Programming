//make sure to make new file!
import java.io.*;
import java.util.*;

public class A733{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         char[] array = f.readLine().toCharArray();
         
         int max = 0;
         for(int k = 0; k < array.length; k++){
            max = Math.max(max,Character.getNumericValue(array[k]));
         }
         
         out.println(max);
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}