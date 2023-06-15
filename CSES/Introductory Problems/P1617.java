//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1617{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      long[] pow2 = new long[N];
      pow2[0] = 1L;
      for(int k = 1; k < N; k++){
         pow2[k] = (2L * pow2[k-1] + MOD)%MOD;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      out.println(pow2[n]);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}