//make sure to make new file!
import java.io.*;
import java.util.*;

public class C57{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
         double n = Double.parseDouble(f.readLine());
         
         int answer = 0;
         for(int k = 3; k <= 360; k++){
            double first = 360.0/(k*2);
            double last = 180.0-360.0/k;
            
            if(n > last || n < first) continue;
            if(n % first == 0){
               answer = k;
               break;
            }
         }
         
         out.println(answer);
      }
      
      
      
      out.close();
   }
   
}