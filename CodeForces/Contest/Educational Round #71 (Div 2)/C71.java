//make sure to make new file!
import java.io.*;
import java.util.*;

public class C71{
   
   public static long MAX = Long.MAX_VALUE;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         char[] array = f.readLine().toCharArray();
         
         long[][] dp = new long[n][2];
         for(int k = 0; k < n; k++){
            Arrays.fill(dp[k],MAX);
         }
         dp[0][0] = b;
         
         for(int k = 0; k < n-1; k++){
            for(int j = 0; j < 2; j++){
               if(dp[k][j] == MAX) continue;
               if(j==0){
                  
                  //check to go to 2 
                  dp[k+1][1] = Math.min(dp[k+1][1],dp[k][j] + a*2 + b*2);
                  if(array[k] == '0' && array[k+1] == '0'){
                     //check stay at 1
                     dp[k+1][0] = Math.min(dp[k+1][0],dp[k][j] + a + b);
                  }
               } else {
                  //j==1
                  
                  //check to stay at 2
                  dp[k+1][1] = Math.min(dp[k+1][1],dp[k][j] + a + b*2);
                  if(array[k] == '0' && array[k+1] == '0'){
                     //check to go to 1
                     dp[k+1][0] = Math.min(dp[k+1][0],dp[k][j] + a*2 + b);
                  }
               }
            }
         }
         
         long answer = MAX;
         if(dp[n-1][0] != MAX) answer = Math.min(answer,dp[n-1][0] + a + b);
         if(dp[n-1][1] != MAX) answer = Math.min(answer,dp[n-1][1] + a*2 + b);
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}