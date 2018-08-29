//The Phone Number
//semi-tutorial
import java.io.*;
import java.util.*;

public class C502b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int x = (int)Math.floor(Math.sqrt(n));
      
      int cur = n-x+1;
      
      while(cur>0){
         for(int k = 0; k < x; k++){
            out.print(cur+k + " ");
         }
         cur-=x;
      }
      
      for(int k = 1; k <= n%x; k++){
         out.print(k + " ");
      }
      
      out.close();
   }
   
}