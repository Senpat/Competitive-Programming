//make sure to make new file!
import java.io.*;
import java.util.*;

public class lights{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n1 = Integer.parseInt(f.readLine());
      
      for(int k = 1; k <= n1; k++){
      
         String s = f.readLine();
         
         int n = s.length();
         int b = 0;
         int e = n/2;
         
         while(e < n){
            if(s.charAt(b) == s.charAt(e)){
               b++;
               e++;
            } else {
               if(b == 0){
                  e++;
               } else {
                  b--;
               }
            }
         }
         
         if(b == 0) out.println("MESSY");
         else out.println("OK");
         
               
      }
      
      out.close();
   }
   
}