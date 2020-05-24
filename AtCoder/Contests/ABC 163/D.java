//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      long[] psums = new long[n+1];
      long[] ssums = new long[n+1];
      
      psums[0] = 0L;
      ssums[n] = (long)n;
      
      for(int k = 1; k <= n; k++){
         psums[k] = (psums[k-1] + k + MOD)%MOD;
      }
      
      for(int k = n-1; k >= 0; k--){
         ssums[k] = (ssums[k+1] + k + MOD)%MOD;
      }
      
      long answer = 0L;
      for(int k = m; k <= n; k++){
         long min = psums[k-1];
         long max = ssums[n-k+1];
         
         answer = ((answer + max - min + 1 + MOD)%MOD+MOD)%MOD;
      }
      
      
      answer = (answer + 1 + MOD)%MOD;
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}