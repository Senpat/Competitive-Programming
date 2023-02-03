//make sure to make new file!
import java.io.*;
import java.util.*;

public class B137{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringJoiner sj = new StringJoiner(" ");
         sj.add("1");
         
         for(int k = n; k > 1; k--){
            sj.add("" + k);
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}