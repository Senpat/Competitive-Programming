//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1730{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int xor = 0;
         for(int k = 0; k < n; k++){
            int i = Integer.parseInt(st.nextToken());
            xor ^= i;
         }
         
         if(xor == 0){
            out.println("second");
         } else {
            out.println("first");
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}