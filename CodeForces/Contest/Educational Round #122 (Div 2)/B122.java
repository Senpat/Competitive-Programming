//make sure to make new file!
import java.io.*;
import java.util.*;

public class B122{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] s = f.readLine().toCharArray();
         int n = s.length;
         int[] array = new int[n];
         int n1 = 0;
         int n0 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s[k]);
            if(array[k] == 0) n0++;
            else n1++;
         }
         
         if(n1!=n0){
            out.println(Math.min(n1,n0));
         } else {
            out.println(Math.min(n1,n0)-1);
         }
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}