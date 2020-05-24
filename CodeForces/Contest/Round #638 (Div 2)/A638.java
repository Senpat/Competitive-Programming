//make sure to make new file!
import java.io.*;
import java.util.*;

public class A638{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] pow2 = new long[35];
      pow2[0] = 1L;
      for(int k = 1; k < 35; k++){
         pow2[k] = 2L*pow2[k-1];
      }
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         long a = 0L;
         long b = 0L;
         
         a = pow2[n];
         
         for(int k = 1; k < n/2; k++){
            a += pow2[k];
         }
         
         for(int k = n/2; k < n; k++){
            b += pow2[k];
         }
         
         out.println(Math.abs(a-b));
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}