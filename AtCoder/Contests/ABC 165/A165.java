//make sure to make new file!
import java.io.*;
import java.util.*;

public class A165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      if(a%n == 0 || b%n == 0 || b/n > a/n){
         out.println("OK");
      } else {
         out.println("NG");
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}