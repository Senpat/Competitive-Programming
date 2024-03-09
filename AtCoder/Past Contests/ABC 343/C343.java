//make sure to make new file!
import java.io.*;
import java.util.*;

public class C343{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long x = 1L;
      long answer = -1L;
      
      while(x*x*x <= n){
         if(palin(x*x*x)) answer = x*x*x;
         
         x++;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean palin(long x){
      char[] c = ("" + x).toCharArray();
      int cn = c.length;
      for(int k = 0; k < cn; k++){
         if(c[k] != c[cn-k-1]) return false;
      }
      return true;
   }
      
}