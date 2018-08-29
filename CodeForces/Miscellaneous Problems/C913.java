//Party Lemonade
import java.io.*;
import java.util.*;

public class C913{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      int[] two = new int[n];
      for(int k = 0; k < n; k++){
         two[k] = (int)Math.pow(2,k);
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      long[] dp = new long[two[n-1]+m];                        //out of memory error
      
      Arrays.fill(dp,Long.MAX_VALUE);
      
      for(int k = 0; k < n; k++) dp[two[k]] = array[k];
      
      for(int k = 1; k < dp.length; k++){
         if(dp[k]<Long.MAX_VALUE){
            for(int j = 0; j < n; j++){
               if(k+two[j]<dp.length){
                  dp[k+two[j]] = Math.min(dp[k+two[j]],dp[k]+array[j]);
               }
            }
         }
      }
      
      long min = Long.MAX_VALUE;
      
      for(int k = m; k < dp.length; k++){
         min = Math.min(min,dp[k]);
      }
      
      out.println(min);
            
      
      out.close();
   }
   
}