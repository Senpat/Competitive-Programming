//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{
   
   public static long MOD;
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      while(true){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long p = Long.parseLong(st.nextToken());
         long a = Long.parseLong(st.nextToken());
         
         if(p+a==0){
            break;
         }
         
         boolean found = false;
         for(long k = 2L; k*k <= p; k++){
            if(p%k == 0){
               found = true;
               break;
            }
         }
         
         if(!found){
            out.println("no");
            continue;
         }
         
         MOD = p;
         
         long a1 = exp(a,p);
         if(a1 == a){
            out.println("yes");
         } else {
            out.println("no");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static long exp(long base, long power){
      if(power == 0) return 1;
      if(power == 1) return (base + MOD) % MOD;
      long ans = exp(base,power/2);
      ans = (ans*ans + MOD) % MOD;
      if(power%2 == 1) ans = (ans*base + MOD) % MOD;
      return (ans + MOD) % MOD;
   }
   
      
}