//make sure to make new file!
import java.io.*;
import java.util.*;

public class A571{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      if(a<=b && a <=c){
         out.println("Yes");
      } else {
         out.println("No");
      }

out.close();
   }
   
      
}