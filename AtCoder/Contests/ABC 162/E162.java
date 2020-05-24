//make sure to make new file!
import java.io.*;
import java.util.*;

public class E162{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      long answer = 0L;
      long[] sums = new long[(int)m+1];
      
      for(int k = m; k >= 1; k--){
         /*long num = ((exp(m/k,n)-sums[(int)k]+MOD)%MOD+MOD)%MOD;
         
         
         for(int j = 1; j*j <= k; j++){
            if(k%j == 0) sums[j] = (sums[j] + num + MOD)%MOD;
         }*/
         
         long num = exp((long)(m/k),n);
         
         for(int j = k*2; j <= m; j += k){
            num = num-sums[j];
            if(num < 0) num += MOD;
         }
         
         sums[k] = num;
         
         
         answer = (answer + k*num + MOD)%MOD;
      }
      
      
      out.println(answer);
      
      
      out.close();
   }
   
   public static long exp(long base, long power){
      if(power == 0) 
         return 1;
      if(power == 1) 
         return (base + MOD) % MOD;
      long ans = exp(base,power/2);
      ans = (ans*ans + MOD) % MOD;
      if(power%2 == 1) ans = (ans*base + MOD) % MOD;
      return (ans + MOD) % MOD;
   }
   
      
}