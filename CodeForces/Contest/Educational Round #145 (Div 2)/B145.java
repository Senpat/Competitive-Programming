//make sure to make new file!
import java.io.*;
import java.util.*;

public class B145{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
         
         //ceil(sqrt(n))-1
         long l = 1L;
         long r = 1000000000L;
         long ans = -1;
         while(l <= r){
            long mid = l + (r-l)/2;
            
            if(mid*mid >= n){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         out.println(ans-1);

      }
      
      
      
      
      out.close();
   }
   
      
}