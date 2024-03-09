//Effects of Anti Pimples
import java.io.*;
import java.util.*;

public class B1876{
   
   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 100005;
      long[] pow = new long[N];
      pow[0] = 1L;
      for(int k = 1; k < N; k++){
         pow[k] = (2L * pow[k-1] + MOD)%MOD;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[] maxes = new long[n];
      
      for(int k = 1; k <= n; k++){
         long max = 0L;
         for(int j = k; j <= n; j += k){
            max = Math.max(max,array[j]);
         }
         maxes[k-1] = max;
      }
      
      Arrays.sort(maxes);
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         answer = (answer + pow[k] * maxes[k] + MOD)%MOD;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}