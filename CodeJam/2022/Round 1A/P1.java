//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         boolean[] addtwice = new boolean[n];
         
         for(int k = n-2; k >= 0; k--){
            if(array[k] < array[k+1]) addtwice[k] = true;
            if(array[k] == array[k+1] && addtwice[k+1]) addtwice[k] = true;
         }
         
         out.print("Case #" + q + ": ");
         for(int k = 0; k < n; k++){
            out.print(array[k]);
            if(addtwice[k]) out.print(array[k]);
         }
         out.println();
      

      }
      
      
      
      
      out.close();
   }
   
      
}