//make sure to make new file!
import java.io.*;
import java.util.*;

public class B1617{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         if(n%2 == 0){
            out.println("2 " + (n-3) + " 1");
         } else {
            int d2 = n/2;
            if(d2%2 == 0){
               out.println((d2-1) + " " + (d2+1) + " 1");
            } else {
               out.println((d2-2) + " " + (d2+2) + " 1");
            }
         }

      }
      
      
      
      
      out.close();
   }
   
      
}