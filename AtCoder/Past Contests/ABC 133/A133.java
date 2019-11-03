//make sure to make new file!
import java.io.*;
import java.util.*;

public class A133{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      int answer = Math.min(n*a,b);
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}