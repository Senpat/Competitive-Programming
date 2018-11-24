//make sure to make new file!
import java.io.*;
import java.util.*;

public class D512{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      long d = Long.parseLong(st.nextToken());
      
      
      if(2*n*m%d == 0){
         long area = 2*n*m/d;                            //times 2 for formula
         
         long bx = 2*n*m/d;
         long cy = 1L;
         while(bx > n){
            //calculate least divisor
            long divisor = 0L;
            for(long k = 2; k <= bx; k++){
               if(bx % k == 0){
                  divisor = k;
                  break;
               }
            }
            bx /= divisor;
            cy *= divisor;
         }
         out.println("YES");
         out.println("0 0");
         out.println(bx + " 0");
         out.println("0 " + cy);
         
                  
      } else out.println("NO");
      
      out.close();
   }
   
}