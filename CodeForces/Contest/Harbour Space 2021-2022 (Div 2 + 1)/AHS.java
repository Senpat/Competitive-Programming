//make sure to make new file!
import java.io.*;
import java.util.*;

public class AHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int answer = n/10;
         if(n%10 == 9) answer++;
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}