//make sure to make new file!
import java.io.*;
import java.util.*;

public class B162{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long answer = 0L;
      
      long cur = 1L;
      
      int i = 0;
      
      while(i < n){
         if(cur % 3 == 0 || cur %  5 == 0){
            i++;
            cur ++;
         } else {
            answer += cur;
            cur++;
            i++;
         }
      }
      
      out.println(answer);
      
      

      
      
      
      
      
      out.close();
   }
   
      
}