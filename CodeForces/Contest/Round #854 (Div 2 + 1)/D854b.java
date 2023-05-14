//make sure to make new file!
import java.io.*;
import java.util.*;
//solves D2 hopefully
public class D854b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         int[] last = new int[m+1];
         int[] prev = new int[n];
         Arrays.fill(last,-1);
         Arrays.fill(prev,-1);
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            prev[k] = last[array[k]];
            last[array[k]] = k;
         }
      
         long[] cold = new long[m+1];
         long[] hot = new long[m+1];
         long[] dec = new long[m+1];
         
         st = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         for(int k = 1; k <= m; k++){
            cold[k] = Long.parseLong(st.nextToken());
            hot[k] = Long.parseLong(st2.nextToken());
            dec[k] = cold[k]-hot[k];
         }
         
         long sum = 0L;
         for(int k = 0; k < n; k++){
            sum += cold[array[k]];
         }
         
         long[] rep = new long[n];
         for(int k = 1; k < n; k++){
            if(array[k] == array[k-1]) rep[k] = dec[array[k]];
         }
         long[] reppsum = new long[n+1];
         reppsum[0] = 0L;
         for(int k = 1; k <= n; k++){
            reppsum[k] = reppsum[k-1] + rep[k-1];
         }
         
         
         long[] dp = new long[n];
         
         dp[0] = 0;
         for(int k = 1; k < n-1; k++){
            //don't jump
            dp[k] = dp[k-1] + rep[k];
            
            //jump from k to prev[array[k+1]]+1 (dec k+1) 
            if(prev[k+1] != -1 && array[k] != array[k+1]){
               long psum = reppsum[k+1]-reppsum[prev[k+1]+1];
               long a2 = dp[prev[k+1]] + psum + dec[array[k+1]];
               dp[k] = Math.max(dp[k],a2);
            }
         }
         
         long answer = sum-reppsum[n];
         
         for(int k = 0; k < n-1; k++){
            answer = Math.min(answer,sum-(dp[k]+reppsum[n]-reppsum[k+1]));
         }
         
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}