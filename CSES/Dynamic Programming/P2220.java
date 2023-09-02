//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2220{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      String as = st.nextToken();
      String bs = st.nextToken();
      
      while(as.length() < bs.length()) as = "0" + as;
      
      int n = as.length();
      int[] a = new int[n];
      int[] b = new int[n];
      for(int k = 0; k < n; k++){
         a[k] = Character.getNumericValue(as.charAt(k));
         b[k] = Character.getNumericValue(bs.charAt(k));
      }
      
      //first n digits, last digit, exactly upper, exactly lower
      //last digit == 10 means it equals 0 and the next one can be anything (including another leading 0)
      long[][][][] dp = new long[n][11][2][2];
      
      if(a[0] == b[0]){
         dp[0][a[0]][1][1] = 1L;
      } else {
         dp[0][b[0]][1][0] = 1L;
         if(a[0] == 0){
            dp[0][10][0][1] = 1L;
         } else {
            dp[0][a[0]][0][1] = 1L;
         }
         for(int k = a[0]+1; k <= b[0]-1; k++){
            dp[0][k][0][0] = 1L;
         }
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= 9; j++){
            for(int h = 0; h <= 9; h++){
               if(h == j || (j == 10 && h == 0)) 
                  continue;
               //0 0
               dp[k+1][h][0][0] += dp[k][j][0][0];  
               
               //1 0
               if(h == b[k+1]){
                  dp[k+1][h][1][0] += dp[k][j][1][0];
               } else if(h < b[k+1]){
                  dp[k+1][h][0][0] += dp[k][j][1][0];
               }
               
               //0 1
               if(h == a[k+1]){
                  dp[k+1][h][0][1] += dp[k][j][0][1];
               } else if(h > a[k+1]){
                  dp[k+1][h][0][0] += dp[k][j][0][1];
               }
               
               //1 1
               if(a[k+1] == b[k+1] && h == a[k+1]){
                  dp[k+1][h][1][1] += dp[k][j][1][1];
               } else if(h == a[k+1]){
                  dp[k+1][h][0][1] += dp[k][j][1][1];
               } else if(h == b[k+1]){
                  dp[k+1][h][1][0] += dp[k][j][1][1];
               } else if(h > a[k+1] && h < b[k+1]){
                  dp[k+1][h][0][0] += dp[k][j][1][1];
               }
                  
               
            }
            
            
            
         }
         
         //process j = 10
         if(a[k+1] == 0){
            //add a 0
            dp[k+1][10][0][1] += dp[k][10][0][1];
            //out.println(dp[k][10][0][1]);
            //out.println(" " + dp[k][0][0][1]);
            for(int h = 1; h <= 9; h++){
               dp[k+1][h][0][0] += dp[k][10][0][1];
            }
         } else {
            dp[k+1][a[k+1]][0][1] += dp[k][10][0][1];
            for(int h = a[k+1]+1; h <= 9; h++){
               dp[k+1][h][0][0] += dp[k][10][0][1];
            }
         }
         
      }
      
      
      long answer = 0L;
      for(int k = 0; k <= 9; k++){
         answer += dp[n-1][k][0][0];
         answer += dp[n-1][k][1][0];
         answer += dp[n-1][k][0][1];
         answer += dp[n-1][k][1][1];
      }
      answer += dp[n-1][10][0][1];
      out.println(answer);
      
      
      out.close();
   }
   
      
}