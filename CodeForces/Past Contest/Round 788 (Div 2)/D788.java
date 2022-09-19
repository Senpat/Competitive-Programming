//make sure to make new file!
import java.io.*;
import java.util.*;

public class D788{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
      
         long l = 1L;
         long r = 100000L;
         long ans = -1;
         while(l <= r){
            long mid = l + (r-l)/2;
            
            //check
            long x1 = mid/3;
            long x2 = mid/3;
            long x3 = mid/3;
            if((mid % 3) >= 1){
               x1++;
            }
            if((mid % 3) >= 2){
               x2++;
            }
            
            long cur = x1*x2*2 + x2*x3*2 + x1*x3*2;
            if(cur >= n){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         
         out.println(ans);
            

      }
      
      
      
      
      out.close();
   }
   
      
}