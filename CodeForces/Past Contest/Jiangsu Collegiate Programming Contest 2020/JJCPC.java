//make sure to make new file!
import java.io.*;
import java.util.*;

public class JJCPC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         double[] prev = new double[n];
         prev[1] = 1.0;
         
         double sum = 1.0;
         for(int k = 2; k < n; k++){
            prev[k] = prev[n%k]+1;
            sum += prev[k];
         }
         
         out.println(sum/(n-1));
      

      }
      
      
      
      
      out.close();
   }
   
      
}