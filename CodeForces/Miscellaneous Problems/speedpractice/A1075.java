//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1075{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      
      long w = Math.max(x-1,y-1);
      long b = Math.max(n-x,n-y);
      
      if(w <= b){
         out.println("white");
      } else {
         out.println("black");
      }

      
      
      
      
      out.close();
   }
   
      
}