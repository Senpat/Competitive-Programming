//make sure to make new file!
import java.io.*;
import java.util.*;

public class A343{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      int answer = (a+b+1)%10;
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}