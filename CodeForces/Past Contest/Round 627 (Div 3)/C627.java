//make sure to make new file!
import java.io.*;
import java.util.*;

public class C627{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         int maxdif = -1;
         int start = -1;
         
         for(int k = 0; k < n; k++){
            if(array[k] == 'R'){
               maxdif = Math.max(k-start,maxdif);
               start = k;
            }
         }
         maxdif = Math.max(maxdif,n-start);
         
         out.println(maxdif);
      

      }
      
      
      
      
      out.close();
   }
   
      
}