//make sure to make new file!
import java.io.*;
import java.util.*;

public class B167{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      int answer = 0;
      answer = Math.min(a,n);
      n -= Math.min(a,n);
      
      n -= Math.min(b,n);
      
      answer -= n;
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}