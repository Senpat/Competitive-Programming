//Divan and Kostomuksha
import java.io.*;
import java.util.*;
//solves D1
//semi-t
public class D1614{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 5000005;
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      
      long[] freq = new long[N];
      long[] count = new long[N];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         freq[array[k]]++;
      }
      
      for(int k = N-1; k >= 1; k--){
         for(int j = k; j < N; j+=k){
            count[k] += freq[j];
         }
      }
      
      long[] dp = new long[N];
      for(int k = 1; k < N; k++){
         dp[k] = (long)k * count[k];
      }
      
      for(int k = N-1; k >= 1; k--){
         for(int j = k*2; j < N; j+=k){
            dp[k] = Math.max(dp[k],dp[j] + (long)k * (count[k]-count[j]));
         }
      }
      
      long answer = 0L;
      for(int k = 1; k < N; k++){
         answer = Math.max(answer,dp[k]);
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}