//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      int[] biggerright = new int[n];
      int[] biggerleft = new int[n];
      
      Stack<Integer> right = new Stack<Integer>();
      //fill biggerright
      for(int k = 0; k < n; k++){
         while(!right.isEmpty() && array[k] > array[right.peek()]){
            int i = right.pop();
            biggerright[i] = array[k];
         }
         right.push(k);
      }
      
      Stack<Integer> left = new Stack<Integer>();
      //fill biggerleft
      for(int k = n-1; k >= 0; k--){
         while(!left.isEmpty() && array[k] > array[left.peek()]){
            int i = left.pop();
            biggerleft[i] = array[k];
         }
         left.push(k);
      }
      
      int answer = 0;
      for(int k = 0; k < n; k++){
         if(biggerright[k] != 0) answer++;
         if(biggerleft[k] != 0)  answer++;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}