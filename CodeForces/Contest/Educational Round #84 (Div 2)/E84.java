//make sure to make new file!
import java.io.*;
import java.util.*;

public class E84{

   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringJoiner sj = new StringJoiner(" ");
      
      long[] p10 = new long[n+2];
      
      p10[0] = 1L;
      
      for(int k = 1; k < n+2; k++){
         p10[k] = (p10[k-1]*10L + MOD)%MOD;
      }
      
      for(int k = 1; k <= n; k++){
         if(k == n) sj.add("10");
         else {
            long leading = (9L*p10[n-k] + MOD)%MOD;
            long trailing = (9L*p10[n-k] + MOD)%MOD;
            long middle = ((long)(n-k-1)*810L + MOD)%MOD;
            if(n-k-2 >= 0){
               middle = (middle*p10[n-k-2]+MOD)%MOD;
            }
            long answer = (leading + middle + trailing + MOD)%MOD;
            sj.add("" + answer);
         }
      }
      
      out.println(sj.toString());
      
      
      
   
      
      
      
      
      
      out.close();
   }
   
      
}