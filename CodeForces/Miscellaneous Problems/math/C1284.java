//New York and Permutation
import java.io.*;
import java.util.*;

public class C1284{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[] fac = new long[(int)n+1];
      
      fac[0] = 1;
      for(long k = 1; k <= n; k++){
         fac[(int)k] = (fac[(int)k-1]*k+m)%m;
      }
      long answer = 0L;
      //long answer = (fac[(int)n]*n+m)%m;
      for(int k = 1; k <= n; k++){
         long add = (fac[k]*fac[(int)n-k+1]+m)%m;
         add = (add*(n-k+1)+m)%m;
         answer = (answer + add +m)%m;
      }
      
      out.println(answer);
      
      

      
      
      
      
      
      out.close();
   }
   
      
}