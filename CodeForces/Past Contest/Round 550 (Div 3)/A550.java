//make sure to make new file!
import java.io.*;
import java.util.*;

public class A550{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         Arrays.sort(array);
         
         boolean fail = false;
         for(int k = 1; k < n; k++){
            if(array[k] != array[k-1]+1){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("No");
         } else {
            out.println("Yes");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}