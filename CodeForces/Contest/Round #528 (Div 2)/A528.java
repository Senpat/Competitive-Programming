//make sure to make new file!
import java.io.*;
import java.util.*;

public class A528{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int n = s.length();
      
      char[] array = new char[n];
      
      
      int cur = n/2;
      if(n%2==0) cur--;
      int[] pm = {-1,1};
      array[0] = s.charAt(cur);
      for(int k = 1; k < n; k++){
         //System.out.println(k + " " + cur + " " + (pm[k%2]*k+cur));
         array[k] = s.charAt(pm[k%2]*k+cur);
         cur+=pm[k%2]*k;
      }
      
      for(int k = 0; k < n; k++){
         out.print(array[k]);
      }
      
      
      
      out.close();
   }
   
}