//Slider Puzzle
import java.io.*;
import java.util.*;

public class E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int[] array = new int[9];
      
      int index = 0;
      for(int k = 0; k < s.length(); k++){
         if(s.charAt(k) != '_'){
            array[index] = Character.getNumericValue(s.charAt(k));
            index++;
         }
      }
      
      int count = 0;
      for(int k = 0; k < 9; k++){
         for(int j = k+1; j < 9; j++){
            if(array[k] > array[j]){
               count++;
            }
         
         }
      }
      
      if(count%2==0){
         out.println("YES");
      } else {
         out.println("NO");
      }
      
      
      out.close();
   }
   
}