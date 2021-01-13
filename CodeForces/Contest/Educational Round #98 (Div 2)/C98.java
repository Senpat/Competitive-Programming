//make sure to make new file!
import java.io.*;
import java.util.*;

public class C98{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         
         int numc = 0;        //(
         int nums = 0;        //[
         
         int answer = 0;
         for(int k = 0; k < array.length; k++){
            if(array[k] == '(') numc++;
            else if(array[k] == '[') nums++;
            else if(array[k] == ')'){
               if(numc > 0){
                  numc--;
                  answer++;
               }
            } else if(array[k] == ']'){
               if(nums > 0){
                  nums--;
                  answer++;
               }
            }
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}