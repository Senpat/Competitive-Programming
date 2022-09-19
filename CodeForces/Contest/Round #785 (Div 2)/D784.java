//make sure to make new file!
import java.io.*;
import java.util.*;

public class D784{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long b = Long.parseLong(st.nextToken());
         long bcd = Long.parseLong(st.nextToken());
         long bn = Long.parseLong(st.nextToken());
      
         st = new StringTokenizer(f.readLine());
         long c = Long.parseLong(st.nextToken());
         long ccd = Long.parseLong(st.nextToken());
         long cn = Long.parseLong(st.nextToken());
         
         
         long bend = b + bcd*(bn-1);
         long cend = c + ccd*(cn-1);
         
         if(ccd % bcd != 0){
            out.println(0);
            continue;
         }
         
         //c and cend must be in the b sequence
         if(c < b || cend > bend){
            out.println(0);
            continue;
         }
         if((c-b)%bcd != 0 || (cend-b)%bcd != 0){
            out.println(0);
            continue;
         }
         
         if(bend < cend+ccd || b > c-ccd){
            out.println(-1);
            continue;
         }
         
         ArrayList<Long> acdlist = new ArrayList<Long>();
         
         for(long k = 1; k*k <= ccd; k++){
            if(ccd % k != 0) continue;
            if(lcm(k,bcd) == ccd) acdlist.add(k);
            if(k*k != ccd) if(lcm(ccd/k,bcd) == ccd) acdlist.add(ccd/k);
         }
         //acdlist.add(ccd);
         
         long answer = 0L;
         for(long acd : acdlist){
            long x = ccd/acd;
            long squared = (x*x + MOD)%MOD;
            answer = (answer + squared + MOD)%MOD;
            
         }
         
         out.println(answer);
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static long lcm(long a, long b){
      return a*b/gcd(a,b);
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