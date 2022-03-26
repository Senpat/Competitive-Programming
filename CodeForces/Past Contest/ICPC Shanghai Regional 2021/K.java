//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n <= 3){
         if(n == 2) out.println("01");
         else out.println("Unlucky");
         out.close();
         return;
      }
      
      int[] answer = new int[n];
      int i = 0;
      int prev = 0;
      if(n % 2 == 1){
         answer[0] = 0;
         answer[1] = 1;
         answer[2] = 0;
         answer[3] = 1;
         answer[4] = 0;
         i = 5;
      }
      
      for(int k = i; k < n; k++){
         if((k-i) % 2 == 0){
            answer[k] = prev;
         } else {
            answer[k] = 1-prev;
         }
         
         prev = answer[k];
      }
      
      
      for(int k = 0; k < n; k++){
         out.print(answer[k]);
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}