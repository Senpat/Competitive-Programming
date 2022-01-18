//make sure to make new file!
import java.io.*;
import java.util.*;

public class C714{

   public static long MOD = 1000000007L;
   public static int MAX = 200005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[][] precalc = new long[10][MAX];
      for(int k = 0; k < 10; k++){
         precalc[k] = calc(k);
      }
      
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long[] digits = new long[10];
         
         long answer = 0;
         while(n > 0){
            answer = (answer + precalc[n%10][m] + MOD)%MOD;
            n/=10;
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   public static long[] calc(int x){
      long[] digits = new long[10];
      digits[x] = 1;
      
      long[] ret = new long[MAX];
      ret[0] = 1;
      for(int k = 1; k < MAX; k++){
         ret[k] = (ret[k-1] + digits[9] + MOD)%MOD;
         shift(digits);
      }
      
      return ret;
   }
   
   public static void shift(long[] digits){
      
      long temp9 = digits[9];
      for(int k = 8; k >= 0; k--){
         digits[k+1] = digits[k];
      }
      digits[0] = temp9;
      digits[1] = (digits[1] + temp9 + MOD)%MOD;
   }
      
}