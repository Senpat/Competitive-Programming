//make sure to make new file!
import java.io.*;
import java.util.*;

public class A626{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      
      int answer = 0;
      
      int num1 = 0; //(
      int num2 = 0; //)
      
      if(array[0] == '(') num1++;
      else num2++;
      
      
      boolean good = num1 == 1;
      if(!good) answer++;
      
      for(int k = 1; k < n; k++){
         if(array[k] == '(') num1++;
         else num2++;
         
         if(!good){
            answer++;
            
         }
         
         if(num1>=num2) good = true;
         else {
            if(good) answer++;
            good = false;
         }
      }
      
      if(num1 != num2) out.println(-1);
      else out.println(answer);
         
      
      
      
      
   
      
      
      
      
      
      out.close();
   }
   
      
}