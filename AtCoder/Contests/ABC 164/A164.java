//make sure to make new file!
import java.io.*;
import java.util.*;

public class A164{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      if(m >= n){
         out.println("unsafe");
      } else {
         out.println("safe");
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}