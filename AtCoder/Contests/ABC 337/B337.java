//make sure to make new file!
import java.io.*;
import java.util.*;

public class B337{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      boolean fail = false;
      for(int k = 1; k < n; k++){
         if(array[k] == 'A' && (array[k-1] == 'B' || array[k-1] == 'C')) fail = true;
         if(array[k] == 'B' && array[k-1] == 'C') fail = true;
         //if(array[k] == 'C' && array[k-1] == 'A') fail = true;
      }
      
      if(fail){
         out.println("No");
      } else {
         out.println("Yes");
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}