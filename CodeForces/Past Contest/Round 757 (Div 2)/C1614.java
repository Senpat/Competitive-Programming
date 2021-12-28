//Divan and bitwise operations
import java.io.*;
import java.util.*;

public class C1614{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] pow2 = new long[200005];
      pow2[0] = 1L;
      for(int k = 1; k < 200005; k++){
         pow2[k] = (pow2[k-1]*2L + MOD)%MOD;
      }
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         long allor = 0L;
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            long or = Long.parseLong(st.nextToken());
            
            allor |= or;
         }
         
         out.println((allor * pow2[n-1] + MOD) % MOD);
      
      }
      
      
      
      
      out.close();
   }
   
      
}