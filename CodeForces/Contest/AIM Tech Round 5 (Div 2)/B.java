//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < 400; k++){
         out.print("5");
      }
      out.println();
      for(int k = 0; k < 399; k++){
         out.print("4");
      }
      out.println("5");
      
      
      out.close();
   }
   
}