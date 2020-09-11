//make sure to make new file!
import java.io.*;
import java.util.*;

public class A524{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int answer = (2*n+m-1)/m + (5*n+m-1)/m + (8*n+m-1)/m;
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}