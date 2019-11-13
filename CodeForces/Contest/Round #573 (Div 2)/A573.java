//make sure to make new file!
import java.io.*;
import java.util.*;

public class A573{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n%4==2){
         out.println("1 B");
      } else {
         int answer = Math.abs(1-(n%4));
         out.println(answer + " A");
      }

      
      
      
      
      out.close();
   }
   
      
}