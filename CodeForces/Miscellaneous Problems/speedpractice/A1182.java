//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1182{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int answer;
      if(n%2 == 1) answer = 0;
      else answer = (int)Math.pow(2,n/2);
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}