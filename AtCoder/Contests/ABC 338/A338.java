//make sure to make new file!
import java.io.*;
import java.util.*;

public class A338{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      
      boolean fail = false;
      
      if(array[0] > 'Z') fail = true;
      for(int k = 1; k < array.length; k++){
         if(array[k] < 'a') fail = true;
      }
      
      if(fail){
         out.println("No");
      } else {
         out.println("Yes");
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}