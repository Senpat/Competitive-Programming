//make sure to make new file!
import java.io.*;
import java.util.*;

public class D574{
   
   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long[] pow100 = new long[15];
      pow100[0] = 1;
      for(int k = 1; k < 15; k++){
         pow100[k] = (pow100[k-1]*100 + MOD) % MOD;
      }
      
      
      
      
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      String s = st.nextToken();
      int m = s.length();
      long[][] array = new long[n][m];
      for(int k = 0; k < m; k++){
         array[0][k] = (long)Character.getNumericValue(s.charAt(k));
      }
      
      for(int k = 1; k < n; k++){
         s = st.nextToken();
         for(int j = 0; j < m; j++){
            array[k][j] = (long)Character.getNumericValue(s.charAt(j));
         }
      }
      
      long answer = 0L;
      for(int k = 0; k < m; k++){
         long sum = 0L;
         for(int j = 0; j < n; j++){
            sum += array[j][k];
         }
         sum = (sum + MOD) % MOD;
         sum = (sum*n*11 + MOD) % MOD;
         sum = (sum*pow100[m-k-1] + MOD) % MOD;
         answer = (answer + sum + MOD) % MOD;
      }
      
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}