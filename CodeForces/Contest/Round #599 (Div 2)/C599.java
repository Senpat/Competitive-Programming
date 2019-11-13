//make sure to make new file!
import java.io.*;
import java.util.*;

public class C599
{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      HashSet<Long> hset = numFactors(n);
      
      if(hset.size() == 0){
         out.println(n);
      } else if(hset.size() == 1){
         for(long i : hset){
            out.println(i);
            break;
         }
      } else {
         out.println(1);
      }
      

      
      
      
      
      out.close();
   }
   
   public static HashSet<Long> numFactors(long n){
      HashSet<Long> factors = new HashSet<Long>();
      long orig = n;
      if( n <= 3) return factors;
      long top = (long)Math.sqrt((double)n);
      if(n%2 == 0){
         factors.add(2L);
         while(n%2 == 0){
            n/=2;
         }
      }
      
      for(long k = 3; k <= top; k+=2){
         if(k > n) break;
         if(factors.size() >= 2) return factors;
         if(n%k == 0){
            factors.add(k);
            while(n%k == 0){
               n/=k;
            }
         }
      }
      
      if(n > 2 && n != orig) factors.add(n);
      
      return factors;
   }
   
      
}