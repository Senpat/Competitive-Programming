//make sure to make new file!
import java.io.*;
import java.util.*;

public class A727{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int tt = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= tt; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         long t = Long.parseLong(st.nextToken());
         
         long d = Math.min(n,t/x);
         
         long answer = (n-d)*d + (1 + d-1) * (d-1)/2L;
        
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}