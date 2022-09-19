//make sure to make new file!
import java.io.*;
import java.util.*;

public class A127{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         if(n == 1){
            out.println("NO");
            continue;
         }
         
         boolean fail = false;
         
         for(int k = 0; k < n; k++){
            if(k == 0 && array[k+1] != array[k]) fail = true;
            if(k == n-1 && array[k-1] != array[k]) fail = true;
            if(k > 0 && k < n-1 && array[k+1] != array[k] && array[k-1] != array[k]) fail = true;
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}