//make sure to make new file!
import java.io.*;
import java.util.*;

public class B164{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int at = c/b;
      if(c%b != 0) at++;
      int bt = a/d;
      if(a%d != 0) bt++;
      
      if(at <= bt){
         out.println("Yes");
      } else {
         out.println("No");
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}