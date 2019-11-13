//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG4{
   
   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long[] pow2 = new long[1005];
      pow2[0] = 1L;
      for(int k = 1; k < pow2.length; k++){
         pow2[k] = (pow2[k-1]*2 + MOD)% MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long answer = 4L;
      answer = (answer * pow2[n-1] + MOD) % MOD;
      answer = (answer * pow2[m-1] + MOD) % MOD;
      
      out.println(answer);
      
      out.close();
   }
   
      
}