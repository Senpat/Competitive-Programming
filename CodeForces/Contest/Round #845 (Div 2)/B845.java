//make sure to make new file!
import java.io.*;
import java.util.*;

public class B845{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int N = 100005;
      long[] fac = new long[N];
      fac[0] = 1L;
      long[] twosum = new long[N];
      twosum[0] = 0L;
      
      for(int k = 1; k < N; k++){
         fac[k] = ((long)k * fac[k-1] + MOD)%MOD;
         
         twosum[k] = (twosum[k-1] + (long)(k-1)*2 + MOD)%MOD;
      }
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         long answer = (twosum[n] * fac[n] + MOD)%MOD;
         
         out.println(answer);
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}