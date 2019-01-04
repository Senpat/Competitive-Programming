//make sure to make new file!
import java.io.*;
import java.util.*;

public class DGB{

   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] fac = new long[n+1];
      
      fac[0] = 1L;
      
      for(int k = 1; k <= n; k++){
         fac[k] = (fac[k-1] * k+MOD)%MOD;
      }
      
      long[] ifac = new long[n+1];
      
      ifac[1] = 1L * n;
      
      for(int k = 1; k < n; k++){
         ifac[k+1] = (ifac[k] * (n-k) + MOD)%MOD;
      }
      
      long answer = fac[n];
      
      for(int k = 1; k < n-1; k++){
         if(fac[n-k] == 0) out.println(k);
         long i = ifac[k];
         
         
         //answer = (answer + fac[n] - (fac[n]/fac[n-k]) + MOD)%MOD;
         answer = (answer + fac[n] - i + MOD)%MOD;
      
      }
      
      out.println((answer+MOD)%MOD);
      
      
      
      out.close();
   }
   
}