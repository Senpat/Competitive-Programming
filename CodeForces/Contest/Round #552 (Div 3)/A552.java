//make sure to make new file!
import java.io.*;
import java.util.*;

public class A552{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int max = Math.max(a,Math.max(b,Math.max(c,d)));
      
      boolean zero = false;
      
      if(max-a > 0 || zero) out.print((max-a) + " ");
      else zero = true;
      
      if(max-b > 0 || zero) out.print((max-b) + " ");
      else zero = true;
      
      if(max-c > 0 || zero) out.print((max-c) + " ");
      else zero = true;
      
      if(max-d > 0 || zero) out.print((max-d) + " ");
      else zero = true;
      
      
      
      out.close();
   }
   
      
}