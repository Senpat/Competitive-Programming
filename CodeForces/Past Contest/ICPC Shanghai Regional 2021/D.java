//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashMap<Long,Long> sqrts = new HashMap<Long,Long>();
      for(long k = 1; k*k < 1000000005L; k++){
          sqrts.put(k*k,k);
       }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int qq = 1; qq <= t; qq++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long p = Long.parseLong(st.nextToken());
         long q = Long.parseLong(st.nextToken());
      
         long gcdpq = gcd(p,q);
         p/=gcdpq;
         q/=gcdpq;
         
         long x2 = p+2*q;
         long y2 = p-2*q;
         
         if(!sqrts.containsKey(x2) || !sqrts.containsKey(y2)){
            out.println("0 0");
            continue;
         }
         
         long x = sqrts.get(x2);
         long y = sqrts.get(y2);
         
         long a = (x+y)/2;
         long b = x-a;
         
         out.println(a + " " + b);
            

      }
      
      
      
      
      out.close();
   }
   
   public static long gcd(long a, long b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
   
      
}