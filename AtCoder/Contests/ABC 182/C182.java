//make sure to make new file!
import java.io.*;
import java.util.*;

public class C182{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] input = f.readLine().toCharArray();
      int n = input.length;
      int[] array = new int[n];
      
      int sum = 0;
      int num1 = 0;
      int num2 = 0;
      for(int k = 0; k < n; k++){
         array[k] = Character.getNumericValue(input[k]);
         
         sum += array[k];
         if(array[k]%3 == 1) num1++;
         if(array[k]%3 == 2) num2++;
      }
      
      sum %= 3;
      
      int answer = -1;
      if(sum == 1){
         if(num1 >= 1 && n > 1) answer = 1;
         else if(num2 >= 2 && n > 2) answer = 2;
      } else if(sum == 2){
         if(num2 >= 1 && n > 1) answer = 1;
         else if(num1 >= 2 && n > 2) answer = 2;
      } else {
         answer = 0;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}