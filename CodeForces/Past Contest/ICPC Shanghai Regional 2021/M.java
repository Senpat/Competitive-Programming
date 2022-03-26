//make sure to make new file!
import java.io.*;
import java.util.*;
import java.text.*;

public class M{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      double n = Double.parseDouble(f.readLine());
      
      DecimalFormat df = new DecimalFormat("0.000000000");
      
      if(n == 1.0){
         out.println(df.format(n));
      } else if(n == 2.0){
         out.println(df.format(.25));
      } else if(n == 3.0){
         out.println(df.format(1.0/12.0));
      } else {
         double answer = 1.0/n;
         for(int k = 0; k < n-3; k++){
            answer /= 2.0;
         }
         answer /= 3.0;
      
         out.println(df.format(answer));
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}