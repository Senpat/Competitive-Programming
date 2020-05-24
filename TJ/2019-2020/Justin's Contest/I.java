//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long MOD = Long.parseLong(st.nextToken());
      
      if(MOD == 1){
         out.println(0);
         out.close();
         return;
      }
      
      long[] fac = new long[100005];
      fac[0] = 1L;
      for(int k = 1; k < 100005; k++){
         fac[k]  = (fac[k-1]*k+MOD)%MOD;
      }
      st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum = (sum + array[k] + MOD)%MOD;
      }
      
      //get n-1 C m-1
      
      long mul = ((long)m+MOD)%MOD;
      for(long k = (long)(n-m+1); k <= (long)(n-1); k++){
         mul = (mul*k+MOD)%MOD;
      }
      
      long answer = (sum*mul+MOD)%MOD;
      out.println(answer);
      

      
      
      
      
      
      out.close();
   }
   
      
}