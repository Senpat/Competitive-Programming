//make sure to make new file!
import java.io.*;
import java.util.*;

public class D65{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      int[] answer = new int[n];
      
      Stack<Integer> stk = new Stack<Integer>();
      for(int k = 0; k < n; k++){
         if(s.charAt(k) == '('){
            if(stk.isEmpty()){
               stk.push(0);
               answer[k] = 0;
            } else {
               stk.push(1-stk.peek());
               answer[k] = stk.peek();
            }
         } else {
            int i = stk.pop();
            answer[k] = i;
         }
      }
      
      for(int k = 0; k < n; k++){
         out.print(answer[k]);
      }
      

      
      
      
      
      out.close();
   }
   
   public static class Bracket{
      char b;
      int i;
      public Bracket(char a, int c){
         a = b;
         i = c;
      }
   }
      
}