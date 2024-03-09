//make sure to make new file!
import java.io.*;
import java.util.*;

public class A320{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int a1 = 1;
      for(int k = 0; k < m; k++) a1 *= n;
      int a2 = 1;
      for(int k = 0; k < n; k++) a2 *= m;
      out.println(a1+a2);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}