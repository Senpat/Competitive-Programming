//make sure to make new file!
import java.io.*;
import java.util.*;

public class AM13{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         int answer = 0;
         for(int k = 1; k <= n; k++){
            if(n%k == 0) answer++;
         }
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}