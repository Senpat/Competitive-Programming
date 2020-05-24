//make sure to make new file!
import java.io.*;
import java.util.*;

public class BOzon{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int n = s.length();
      
      char[] array = s.toCharArray();
      
      //find first (
      int first = n+2;
      for(int k = 0; k < n; k++){
         if(array[k] == '('){
            first = k;
            break;
         }
      }
      
      //find last )
      
      int last = -1;
      for(int k = n-1; k >= 0; k--){
         if(array[k] == ')'){
            last = k;
            break;
         }
      }
      
      
      if(first > last){
         out.println("0");
         out.close();
         return;
      }
      
      out.println("1");
      
      PriorityQueue<Integer> answer = new PriorityQueue<Integer>();
      
      
      while(first < last){
         answer.add(first);
         answer.add(last);
         
         int i = first+1;
         
         while(i < n && array[i] != '('){
            i++;
         }
         
         if(i >= n) break;
         
         int i2 = last-1;
         while(i2 >= 0 && array[i2] != ')'){
            i2--;
         }
         
         if(i2 < 0) break;
         
         first = i;
         last = i2;
      }
      
      out.println(answer.size());
      
      while(!answer.isEmpty()){
         out.print(answer.poll()+1 + " ");
      }
      

      
      
      
      
      
      out.close();
   }
   
      
}