//The Bits
import java.io.*;
import java.util.*;

public class B502{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s1 = f.readLine();
      String s2 = f.readLine();
      
      long sin1 = 0L;
      long sin0 = 0L;
      long dub0 = 0L;
      long num1 = 0L;
      
      for(int k = 0; k < n; k++){
         char c1 = s1.charAt(k);
         char c2 = s2.charAt(k);
         
         if(c1=='0'){
            if(c2 == '0'){
               dub0++;
            } else {
               sin0++;
            }
         } else {
            num1++;
            if(c2=='0'){
               sin1++;
            }
         }
      }
      
      long answer = sin1*sin0 + dub0*num1;
      
      out.println(answer);
      
      out.close();
   }
   
}