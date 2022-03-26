//make sure to make new file!
import java.io.*;
import java.util.*;

public class B769{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int i = 1;
         while(i*2 <= n-1){
            i *= 2;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = n-1; k >= i; k--){
            sj.add("" + k);
         }
         sj.add("0");
         for(int k = 1; k < i; k++){
            sj.add("" + k);
         }
         out.println(sj.toString());
         

      }
      
      
      
      
      out.close();
   }
   
      
}