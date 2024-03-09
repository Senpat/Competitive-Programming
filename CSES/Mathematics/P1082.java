//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1082{

   public static long MOD = 1000000007L;
   public static long i2 = 500000004L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long answer = 0L;
      
      long i = 1L;
      
      while(i <= n){
         long freq = n/i;
         //figure out how many other numbers have the same freq
         long l = i;
         long r = n;
         long ans = i;
         
         while(l <= r){
            long mid = l + (r-l)/2L;
            
            if(n/mid == freq){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         long s1 = (i+r + MOD)%MOD;
         long s2 = (r-i+1 + MOD)%MOD;
         long sum = (s1*s2 + MOD)%MOD;
         long prod = (sum * i2 + MOD)%MOD;
         prod = (prod * freq + MOD)%MOD;
         answer += prod;
         if(answer >= MOD) answer -= MOD;
         
         i = r+1;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}