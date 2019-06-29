//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("p1.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("p1.out")));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         String s = f.readLine();
         
         int n = s.length()-1;
         int b = 0;
         for(int k = 1; k <= n; k++){
            if(s.charAt(k) == 'B'){
               b++;
            }
         }
         int thresh = n/2;
         if(n%2 == 1) thresh++;
         boolean answer = (b >= thresh && b < n && b > 0);
         if(answer){
            out.println("Case #" + q + ": Y");
         } else {
            out.println("Case #" + q + ": N");
         }
      }            
            

      
      
      
      
      out.close();
   }
   
      
}