//Three Displays
import java.io.*;
import java.util.*;

public class C987{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      int[] c = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++) c[k] = Integer.parseInt(st.nextToken());
      
      int[][] dp = new int[n][3];
      
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Integer.MAX_VALUE);
      
      for(int k = 0; k < n; k++){
         dp[k][0] = c[k];
         for(int j = 0; j < k; j++){
            if(array[j] < array[k]){
               for(int x = 0; x < 2; x++){
                  if(dp[j][x]!=Integer.MAX_VALUE){
                     dp[k][x+1] = Math.min(dp[k][x+1],dp[j][x]+c[k]);
                  }
               }
            }
         }
      }
      
      int min = Integer.MAX_VALUE;
      
      for(int k = 0; k < n; k++){
         min = Math.min(min,dp[k][2]);
      }
      
      if(min==Integer.MAX_VALUE) out.println(-1);
      else out.println(min);
      
      
      
      
      out.close();
   }
   
}