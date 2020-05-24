//make sure to make new file!
import java.io.*;
import java.util.*;

public class C162{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long answer = 0L;
      
      for(long k = 1; k <= n; k++){
         for(long j = 1; j <= n; j++){
            for(long h = 1; h <= n; h++){
               answer += gcd(gcd(k,j),h);
            }
         }
      }
      
      out.println(answer);
      
      

      
      
      
      
      
      out.close();
   }
   
   public static long gcd(long a, long b){
      if(a==0) return b;
      if(b==0) return a;
      
      if(a==b) return a;
      
      if(a > b)return gcd(a%b,b);
      return gcd(a,b%a);
   }
      
}