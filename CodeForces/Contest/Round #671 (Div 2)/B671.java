//make sure to make new file!
import java.io.*;
import java.util.*;

public class B671{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
         
         long sum = 0L;
         long pow2 = 1L;
         
         int answer = 0;
      
         while(sum <= n){
            answer++;
            pow2 *= 2L;
            sum += (pow2-1)*pow2/2;
         }
         
         out.println(answer-1);
            
            
      }
      
      
      
      
      out.close();
   }
   
      
}