//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1645{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Stack<Integer> stack = new Stack<Integer>();       //stores indeces in increasing order
      
      int[] answer = new int[n+1];
      for(int k = 1; k <= n; k++){
         while(!stack.isEmpty() && array[stack.peek()] >= array[k]){
            stack.pop();
         }
         
         if(!stack.isEmpty()){
            answer[k] = stack.peek();
         }
         
         stack.push(k);
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}