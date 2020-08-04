//make sure to make new file!
import java.io.*;
import java.util.*;

public class B660{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int num8 = (n+3)/4;
         
         StringBuilder sb = new StringBuilder();
         for(int k = 0; k < n-num8; k++) sb.append("9");
         for(int k = 0; k < num8; k++) sb.append("8");
         
         out.println(sb.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}