//make sure to make new file!
import java.io.*;
import java.util.*;

public class CRR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         Stack<Character> stk = new Stack<Character>();
         
         for(int k = 0; k < n; k++){
            if(stk.isEmpty()) stk.push(array[k]);
            else{
               if(array[k] == 'A') stk.push(array[k]);
               else stk.pop();
            }
         }
         
         out.println(stk.size());
      

      }
      
      
      
      
      out.close();
   }
   
      
}