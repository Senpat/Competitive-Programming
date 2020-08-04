//The Football Season
import java.io.*;
import java.util.*;

public class C1244{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      long w = Long.parseLong(st.nextToken());
      long d = Long.parseLong(st.nextToken());     
      
      for(long y = 0; y < w; y++){
         if((p-y*d)%w == 0 && (p-y*d)/w+y <= n && p >= y*d){
            long x = (p-y*d)/w;
            long z = n-y-x;
            out.println(x + " " + y + " " + z);
            out.close();
            return;
         }
      }
      
      out.println(-1);

      
      
      
      
      
      out.close();
   }
   
      
}