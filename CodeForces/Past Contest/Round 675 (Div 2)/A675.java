//make sure to make new file!
import java.io.*;
import java.util.*;

public class A675{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         
         out.println(a+b+c-1);

      }
      
      
      
      
      out.close();
   }
   
      
}