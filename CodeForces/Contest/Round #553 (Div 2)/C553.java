//make sure to make new file!
import java.io.*;
import java.util.*;

public class C553{
   
   public static int MAX2;
   public static long[] pre2;
   public static long[] pow2;
   public static long MOD = 1000000007;
   public static double log2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long l = Long.parseLong(st.nextToken());
      long r = Long.parseLong(st.nextToken());
      
      MAX2 = 65;
      
      pow2 = new long[MAX2];
      pow2[0] = 1L;
      for(int k = 1; k < MAX2; k++){
         pow2[k] = pow2[k-1] * 2;
      }
      
      pre2 = new long[MAX2];
      pre2[0] = pow2[0];
      pre2[1] = pow2[1];
      for(int k = 2; k < MAX2; k++){
         pre2[k] = (pow2[k] + pre2[k-2] + MOD) % MOD;
      }
      
      log2 = Math.log(2);
      
      
      long answer = calc(r)-calc(l-1);
      //out.println(calc(r) + " " + calc(l-1));
      while(answer < 0) answer += MOD;
      

      out.println(answer);
      
      
      
      out.close();
   }
   
   public static long calc(long i){
      if(i == 0) return 0;
      int log = (int)(Math.log(i)/log2 + 1e-10);
      
      long numodd;
      long numeven;
      if(log == 0){
         numodd = 1;
         numeven = 0;
      } else if(log == 1){
         numodd = 1;
         numeven = (i == 3 ? 2 : 1);
      } else if(log%2 == 1){
         numeven = (pre2[log-2] + (i-pow2[log] + 1) + MOD) % MOD;
         numodd = pre2[log-1];
      
      } else {
         numeven = pre2[log-1];
         numodd = (pre2[log-2] + (i-pow2[log] + 1) + MOD) % MOD;
      }
      //System.out.println(i + " " + log + " " + numodd + " " + numeven + " " + odd(numodd) + " " + even(numeven));
      return (odd(numodd) + even(numeven) + MOD) % MOD;
   
   
   }
   
   public static long odd (long n){
      return (n*n + MOD) % MOD;
   }
   
   public static long even(long n){
      return (n*n + n + MOD) % MOD;
   }
   
      
}