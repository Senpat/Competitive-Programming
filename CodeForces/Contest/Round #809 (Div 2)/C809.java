//make sure to make new file!
import java.io.*;
import java.util.*;

public class C809{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         if(n % 2 == 1){
            long ans1 = 0L;
            for(int k = 1; k < n-1; k += 2){
               ans1 += Math.max(0L,Math.max(array[k-1],array[k+1])+1 - array[k]);
            }
            out.println(ans1);
         } else {
            //1 skip
            long[][] dp = new long[n][2];
            for(int k = 0; k < n; k++){
               dp[k][0] = Long.MAX_VALUE;
               dp[k][1] = Long.MAX_VALUE;
            }
            
            dp[1][0] = Math.max(0L,Math.max(array[0],array[2])+1 - array[1]);
            dp[2][1] = Math.max(0L,Math.max(array[1],array[3])+1 - array[2]);;
            
            for(int k = 2; k < n-1; k++){
               //no skip
               if(dp[k-2][0] != Long.MAX_VALUE) dp[k][0] = dp[k-2][0] + Math.max(0L,Math.max(array[k-1],array[k+1])+1 - array[k]);
               
               if(dp[k-2][1] != Long.MAX_VALUE) dp[k][1] = dp[k-2][1] + Math.max(0L,Math.max(array[k-1],array[k+1])+1 - array[k]);
               
               //skip
               if(k-3 > 0){
                  if(dp[k-3][0] != Long.MAX_VALUE) dp[k][1] = Math.min(dp[k][1],dp[k-3][0] + Math.max(0L,Math.max(array[k-1],array[k+1])+1 - array[k]));
               }
            }
            
            long answer = Math.min(dp[n-2][0],dp[n-2][1]);
            answer = Math.min(dp[n-3][0],answer);
            out.println(answer);
         
         }
         
            
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}