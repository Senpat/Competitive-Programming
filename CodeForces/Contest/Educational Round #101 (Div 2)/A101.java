//make sure to make new file!
import java.io.*;
import java.util.*;

public class A101{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         
         int open = -1;
         int closed = -1;
         
         for(int k = 0; k < array.length; k++){
            if(array[k] == '(') open = k;
            else if(array[k] == ')') closed = k;
         }
         
         if(array.length % 2 == 0 && open != array.length-1 && closed != 0){
            out.println("YES");
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}