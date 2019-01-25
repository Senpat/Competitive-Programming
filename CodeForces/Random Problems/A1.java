//Theatre Square
import java.io.*;
import java.util.*;

public class A1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Integer.parseInt(st.nextToken());
      long m = Integer.parseInt(st.nextToken());
      long a = Integer.parseInt(st.nextToken());
      long ji = 0;
      long jibig = 0;
      if(m%a == 0)
      ji = m/a;
      else
      ji = m/a+1;
      
      if(n%a == 0)
      jibig = n/a;
      else 
      jibig = n/a+1;
      out.print(ji*jibig);
      
      out.close();
   }
   
}