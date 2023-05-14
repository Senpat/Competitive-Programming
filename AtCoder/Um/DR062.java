//make sure to make new file!
import java.io.*;
import java.util.*;

public class DR062{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      /*
      If you do all p, you will get g points.
      Every time you do a g instead of p, you lose one point no matter what the original gesture is:
         g: 1 for p, 0 for g, so 1 -> 0
         p: 0 for p, -1 for g, so 0 -> -1
      you are forced to do (n+1)/2 g, so subtract that
      */
      
      int g = 0;
      for(int k = 0; k < n; k++){
         if(array[k] == 'g') g++;
      }
      
      int answer = g - (n+1)/2;
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}