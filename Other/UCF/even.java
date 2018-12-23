//make sure to make new file!
import java.io.*;
import java.util.*;
import java.lang.*;

public class even{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(f.readLine());
         int answer = (int)Math.pow(2,left(i)) - i;
         
         
         out.println("Pokemon " + k + ": " + answer);
      }
         
      
      out.close();
   }
   
   public static int left(int i){
      int c = 0;
      while(i > 0){
         c++;
         i >>= 1;
      }
      return c;
   }
   
}