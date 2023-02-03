//make sure to make new file!
import java.io.*;
import java.util.*;

public class BH23{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         if(n == 3){
            out.println("NO");
            continue;
         } 
         
         int x = 1;
         int y = -1;
         
         if(n % 2 == 1){
            int xc = n/2;
            int yc = n/2-1;
            
            x = yc;
            y = -1*xc;
         }
         
         out.println("YES");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            if(k%2 == 0){
               sj.add("" + x);
            } else {
               sj.add("" + y);
            }
         }
         out.println(sj.toString());
        
      
      }
      
      
      
      
      out.close();
   }
   
      
}