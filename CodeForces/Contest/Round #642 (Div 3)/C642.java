//make sure to make new file!
import java.io.*;
import java.util.*;

public class C642{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] answer = new long[500005];
      answer[1] = 0L;
      long i = 1L;
      for(int k = 3; k < 500005; k+=2){
         answer[k] = answer[k-2] + 8L*i*i;
         i++;
      }
         
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         out.println(answer[n]);

      }
      
      
      
      
      out.close();
   }
   
      
}