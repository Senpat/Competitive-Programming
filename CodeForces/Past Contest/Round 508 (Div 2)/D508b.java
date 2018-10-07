//Slime
//semi-tutorial
//wrong on test 35, way more complicated than necessary
import java.io.*;
import java.util.*;

public class D508b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      
      long max = 0L;
      long min = Long.MAX_VALUE;
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum+=array[k];
         max = Math.max(max,array[k]);
         min = Math.min(min,array[k]);
      }
      
      if(n==1){
         out.println(array[0]);
         out.close();
         System.exit(0);
      }
      
      long[][] dp = new long[n][2];
      
      dp[0][0] = array[0];
      dp[0][1] = array[0]*-1;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= 1; j++){
            //add positive
            dp[k+1][0] = Math.max(dp[k+1][0],dp[k][j]+array[k+1]);
            
            //add negative
            dp[k+1][1] = Math.max(dp[k+1][1],dp[k][j]-array[k+1]);
         }
      }
      
      long answer = Math.max(dp[n-1][0],dp[n-1][1]);
      
      
      if(answer == sum){
         answer-=2*min;
      } else if(answer == sum*-1){
         answer-=2*max;
      }
      
      
      out.println(answer);
      
      out.close();
   }
   
}