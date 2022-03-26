//make sure to make new file!
import java.io.*;
import java.util.*;

public class B124{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         if(n > 19){
            out.println("NO");
         } else {
            out.println("YES");
            long pow3 = 1L;
            for(int k = 0; k < n; k++){
               out.print(pow3 + " ");
               pow3 *= 3L;
            }
            out.println();
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}