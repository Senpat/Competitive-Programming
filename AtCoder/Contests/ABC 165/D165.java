//make sure to make new file!
import java.io.*;
import java.util.*;

public class D165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      long n = Long.parseLong(st.nextToken());
      
      long value = Math.min(b-1,n);
      
      long answer = (a*value)/b - a * (value/b);
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}