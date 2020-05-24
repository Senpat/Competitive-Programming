//make sure to make new file!
import java.io.*;
import java.util.*;
//danny, O(N^2)
public class E1027b{
   
   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(m == 1){
         out.println("0");
         out.close();
         return;
      }
      
      
      long[] array = new long[n+1];            //number of ways such that max chunk is n
      array[0] = 0;
      array[1] = 1;
      
      for(int i = 2; i <= n; i++){
         long[] dp = new long[n+1];
         
         dp[0] = 1;
         
         long sum = 1L;
         for(int k = 1; k <= n; k++){
            dp[k] = sum;
            sum += dp[k];
            if(k >= i){
               sum -= dp[k-i];
            }
            
            sum = (sum + MOD)%MOD;  
         }
         
         array[i] = dp[n];
         
      }
      
      //psums
      long[] psums = new long[n+2];
      
      psums[0] = 0L;
      
      for(int k = n+1; k >= 1; k--){
         psums[k] = array[k-1];      
         if(k > 1) array[k-1]-=array[k-2];
      }
      
      long answer = 0L;
      
      for(int k = 1; k <= n; k++){
         if(k >= (int)m) 
            continue;
         int i = m/k;
         if(k*i==m) i--;
         i = Math.min(i,n);
         long prod = (array[k]*psums[i+1]+MOD)%MOD;
         answer = (answer + prod + MOD)%MOD;
      }
      
      answer = (answer*2 + MOD)%MOD;
      out.println(answer);
      
      
      
      
      
      out.close();
   }
}