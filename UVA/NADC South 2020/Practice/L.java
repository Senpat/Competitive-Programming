//make sure to make new file!
import java.io.*;
import java.util.*;

public class L{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      int cur = a+b;
      int answer = 0;
      while(cur >= c){
         answer += cur/c;
         cur = cur/c+cur%c;
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}