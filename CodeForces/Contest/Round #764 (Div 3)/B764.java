//make sure to make new file!
import java.io.*;
import java.util.*;

public class B764{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         
         long at = b-(c-b);
         if(at > 0 && at % a == 0){
            out.println("YES");
            continue;
         }
         
         long bt = a + (c-a)/2;
         if((c-a)%2 == 0 && bt % b == 0){
            out.println("YES");
            continue;
         }
         
         long ct = b + (b-a);
         if(ct > 0 && ct % c == 0){
            out.println("YES");
            continue;
         }
         
         out.println("NO");

      }
      
      
      
      
      out.close();
   }
   
      
}