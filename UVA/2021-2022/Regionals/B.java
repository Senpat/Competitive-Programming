//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] input = f.readLine().toCharArray();
      
      int[] array = new int[4];
      for(int k = 0; k < 4; k++) array[k] = Character.getNumericValue(input[k]);
      
      for(int i = 8; i > 0; i /= 2){
         char[] answer = new char[4];
         for(int k = 0; k < 4; k++){
            if(array[k] >= i){
               answer[k] = '*';
               array[k] -= i;
            } else {
               answer[k] = '.';
            }
         }
         
         out.println(answer[0] + " " + answer[1] + "   " + answer[2] + " " + answer[3]);
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}