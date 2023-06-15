//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1069{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int maxlen = 1;
      int len = 1;
      for(int k = 1; k < n; k++){
         if(array[k] == array[k-1]){
            len++;
            maxlen = Math.max(maxlen,len);
         } else {
            len = 1;
         }
      }
      
      out.println(maxlen);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}