//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSA{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = 1;
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long p = Long.parseLong(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         long y = Long.parseLong(st.nextToken());
         
         long answer = p*x + p/(n-1)*y;
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}