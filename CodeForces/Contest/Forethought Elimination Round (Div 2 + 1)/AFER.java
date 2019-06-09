//make sure to make new file!
import java.io.*;
import java.util.*;

public class AFER{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int a = 0;
      for(int k = 0; k < s.length(); k++){
         if(s.charAt(k) == 'a') a++;
      }
      
      int answer = Math.min(s.length(),a*2-1);
      
      out.println(answer);
      

      
      
      
      
      out.close();
   }
   
      
}