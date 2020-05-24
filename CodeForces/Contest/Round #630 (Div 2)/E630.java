//make sure to make new file!
import java.io.*;
import java.util.*;

public class E630{

   public static long MOD = 998244353; 
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int l = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      
      long exp = exp(r-l+1,n*m-1);
      if((r-l)%2 == 1){
         long answer = (exp*(r-l+1)/2+MOD)%MOD;
         out.println(answer);
      } else {
         long answer = (exp*((r-l+1)/2+((n*m)%2 == r%2 ? 0 : 1))+MOD)%MOD;
         out.println(answer);
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