//make sure to make new file!
import java.io.*;
import java.util.*;

public class C1886b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         long x = Long.parseLong(f.readLine());
         
         long size = (long)n;
         int si = 0;
         
         while(x > size){
            x -= size;
            si++;
            size--;
         }
         
         //get order that you remove all of the characters
         //stores indeces in increasing order
         Stack<Integer> stack = new Stack<Integer>();
         int[] order = new int[n];
         int i = 0;
         for(int k = 0; k < n; k++){
            if(!stack.isEmpty()){
               while(!stack.isEmpty() && array[stack.peek()] > array[k]){
                  order[stack.pop()] = i++;
               }
            }
            stack.push(k);
         }
         
         while(!stack.isEmpty()){
            order[stack.pop()] = i++;
         }
         
         /*
         for(int k = 0; k < n; k++){
            out.print(order[k] + " ");
         }
         out.println();
         */
         
         char c = '?';
         for(int k = 0; k < n; k++){
            if(order[k] >= si){
               x--;
               if(x == 0){
                  c = array[k];
                  break;
               }
            }
         }
         
         out.print(c);
               
      

      }
      
      
      
      
      out.close();
   }
   
      
}