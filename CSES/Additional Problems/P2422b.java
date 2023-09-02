//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2422b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      long thresh = n*n/2;
      
      long l = 1L;
      long r = 1000000000000L;
      long ans = -1;
      while(l <= r){
         long mid = l + (r-l)/2;
         
         //count # of numbers < mid
         long num = 0L;
         for(long k = 1; k <= n; k++){
            num += Math.min(n,(mid-1)/k);
         }
         
         if(num <= thresh){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      out.println(ans);
      
      
      
      
      
      
      out.close();
   }
   
      
}