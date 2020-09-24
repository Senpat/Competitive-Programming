//make sure to make new file!
import java.io.*;
import java.util.*;

public class A671{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] s = f.readLine().toCharArray();
         boolean[] array = new boolean[n];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s[k]) % 2 == 0;
         }
         
         boolean found = false;
         
         if(n%2 == 0){
            //find even number in even position
            
            for(int k = 1; k < n; k+= 2){
               found |= array[k];
            }
            
            if(found) out.println(2);
            else out.println(1);
            
         } else {
            //find odd in odd
            
            
            for(int k = 0; k < n; k += 2){
               found |= !array[k];
            }
            
            if(found) out.println(1);
            else out.println(2);
         }
         
      }
      
      
      
      
      out.close();
   }
   
      
}