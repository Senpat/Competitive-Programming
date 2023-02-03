//make sure to make new file!
import java.io.*;
import java.util.*;

public class APR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();
         
         int ones = 0;
         if(array[0] == '1') ones++;
         
         ArrayList<Character> answer = new ArrayList<Character>();
         
         for(int k = 1; k < n; k++){
            if(array[k] == '0') answer.add('+');
            else {
               if(ones % 2 == 1) answer.add('-');
               else answer.add('+');
               ones++;
            }
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n-1; k++){
            sj.add("" + answer.get(k));
         }
         out.println(sj.toString());

      }  
      
      
      
      
      out.close();
   }
   
      
}