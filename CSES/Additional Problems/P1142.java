//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1142{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Stack<Integer> stack = new Stack<Integer>();
      //stores index of closest smaller element
      int[] left = new int[n];
      Arrays.fill(left,-1);
      
      for(int k = 0; k < n; k++){
         while(!stack.isEmpty() && array[stack.peek()] >= array[k]){
            stack.pop();
         }
         
         if(!stack.isEmpty()){
            left[k] = stack.peek();
         }
         stack.push(k);
      
      }
      
      stack = new Stack<Integer>();
      int[] right = new int[n];
      Arrays.fill(right,n);
      
      for(int k = n-1; k >= 0; k--){
         while(!stack.isEmpty() && array[stack.peek()] >= array[k]){
            stack.pop();
         }
         
         if(!stack.isEmpty()){
            right[k] = stack.peek();
         }
         stack.push(k);
      
      }
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         long cur = array[k] * ((right[k]-1) - (left[k]+1) + 1);
         answer = Math.max(answer,cur);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}