//make sure to make new file!
import java.io.*;
import java.util.*;

public class D166{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      HashMap<Long,Long> hmap = new HashMap<Long,Long>();
      
      long MAXN = 200;
      for(long k = 0; k < MAXN; k++){
         hmap.put(k*k*k*k*k,k);
      }
      
      for(long k = 0; k < MAXN; k++){
         long a = k*k*k*k*k;
         long b5 = a-n;
         long b = -1;
         boolean neg = b5 < 0;
         b5 = Math.max(b5,-1*b5);
         if(hmap.containsKey(b5)){
            b = hmap.get(b5);
         } else {
            continue;
         }
         
         if(neg){
            b *= -1;
         }
         
         out.println(k + " " + b);
         break;
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}