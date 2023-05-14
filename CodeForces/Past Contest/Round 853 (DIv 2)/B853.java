//make sure to make new file!
import java.io.*;
import java.util.*;

public class B853{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         String s = f.readLine();
         
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
         }
         
         int[] flip = new int[n/2];
         for(int k = 0; k < n/2; k++){
            if(array[k] != array[n-k-1]){
               flip[k] = 1;
            }
         }
         
         //check that 1s in flip are all consecutive
         int startrow = 0;
         for(int k = 0; k < n/2; k++){
            if(flip[k] == 1){
               if(k == 0 || flip[k-1] == 0) startrow++;
            }
         }
         
         if(startrow <= 1){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}