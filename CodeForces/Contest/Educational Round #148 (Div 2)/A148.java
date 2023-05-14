//make sure to make new file!
import java.io.*;
import java.util.*;

public class A148{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         boolean found = false;
         for(int k = 1; k < n/2; k++){
            if(array[k] != array[k-1]) found = true;
         }
         
         if(found){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}