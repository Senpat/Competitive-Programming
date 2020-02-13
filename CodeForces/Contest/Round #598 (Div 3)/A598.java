//make sure to make new file!
import java.io.*;
import java.util.*;

public class A598{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long n = Long.parseLong(st.nextToken());
         long s = Long.parseLong(st.nextToken());
         
         String answer = "NO";
         if(n*a <= s){ if(b >= s-n*a) answer = "YES";
         } else {if(s-s/n*n <= b) answer = "YES";}
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}