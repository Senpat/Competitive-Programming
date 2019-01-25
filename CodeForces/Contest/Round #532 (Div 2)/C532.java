//make sure to make new file!
import java.io.*;
import java.util.*;

public class C532{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      double n = Double.parseDouble(st.nextToken());
      double r = Double.parseDouble(st.nextToken());
      
      double answer = r/((1/Math.sin(Math.PI/n))-1);
      
      out.println(answer);
      
      
      out.close();
   }
   
}