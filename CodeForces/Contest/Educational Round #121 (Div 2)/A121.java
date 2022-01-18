//make sure to make new file!
import java.io.*;
import java.util.*;

public class A121{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         
         Arrays.sort(array);
         
         for(int k = 0; k < array.length; k++){
            out.print(array[k]);
         }
         out.println();
      

      }
      
      
      
      
      out.close();
   }
   
      
}