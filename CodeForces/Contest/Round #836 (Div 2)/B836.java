//make sure to make new file!
import java.io.*;
import java.util.*;

public class B836{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringJoiner sj = new StringJoiner(" ");
         
         if(n%2 == 1){
            for(int k = 0; k < n; k++) sj.add("1");
         } else {
            for(int k = 0; k < n-2; k++) sj.add("2");
            sj.add("1 3");
         }
         
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}