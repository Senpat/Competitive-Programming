//make sure to make new file!
import java.io.*;
import java.util.*;

public class A182{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      int answer = 2*a+100-b;
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}