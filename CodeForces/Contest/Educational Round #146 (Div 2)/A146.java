//make sure to make new file!
import java.io.*;
import java.util.*;

public class A146{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long k = Long.parseLong(st.nextToken());
         
         if(n%2 == 0 || (k%2 == 1 && k <= n)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}