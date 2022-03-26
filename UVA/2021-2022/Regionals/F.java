//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      //(), [], {}, <>
      long[][][][][] dp = new long[n][n+1][n+1][n+1][n+1];
      
      
      
      if(array[0] == '?'){
         dp[0][1][0][0][0] = 1L;
         dp[0][0][1][0][0] = 1L;
         dp[0][0][0][1][0] = 1L;
         dp[0][0][0][0][1] = 1L;
      } else if(array[0] == '('){
         dp[0][1][0][0][0] = 1L;
      } else if(array[0] == '['){
         dp[0][0][1][0][0] = 1L;
      } else if(array[0] == '{'){
         dp[0][0][0][1][0] = 1L;
      } else if(array[0] == '<'){
         dp[0][0][0][0][1] = 1L;
      } else {
         out.println(0);
         out.close();
         return;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= n; j++){
            for(int h = 0; h <= n; h++){
               for(int g = 0; g <= n; g++){
                  for(int i = 0; i <= n; i++){
                     if(dp[k][j][h][g][i] == 0) continue;
                     
                     if(array[k+1] == '?'){
                        //add open
                        dp[k+1][j+1][h][g][i] += dp[k][j][h][g][i];
                        dp[k+1][j][h+1][g][i] += dp[k][j][h][g][i];
                        dp[k+1][j][h][g+1][i] += dp[k][j][h][g][i];
                        dp[k+1][j][h][g][i+1] += dp[k][j][h][g][i];
                     
                        //add closed
                        if(j > 0) dp[k+1][j-1][h][g][i] += dp[k][j][h][g][i];
                        if(h > 0) dp[k+1][j][h-1][g][i] += dp[k][j][h][g][i];
                        if(g > 0) dp[k+1][j][h][g-1][i] += dp[k][j][h][g][i];
                        if(i > 0) dp[k+1][j][h][g][i-1] += dp[k][j][h][g][i];
                     } else if(array[k+1] == '('){
                        dp[k+1][j+1][h][g][i] += dp[k][j][h][g][i];
                     } else if(array[k+1] == '['){
                        dp[k+1][j][h+1][g][i] += dp[k][j][h][g][i];
                     } else if(array[k+1] == '{'){
                        dp[k+1][j][h][g+1][i] += dp[k][j][h][g][i];
                     } else if(array[k+1] == '<'){
                        dp[k+1][j][h][g][i+1] += dp[k][j][h][g][i];
                     } else if(array[k+1] == ')'){
                        if(j > 0) dp[k+1][j-1][h][g][i] += dp[k][j][h][g][i];
                     } else if(array[k+1] == ']'){
                        if(h > 0) dp[k+1][j][h-1][g][i] += dp[k][j][h][g][i];
                     } else if(array[k+1] == '}'){
                        if(g > 0) dp[k+1][j][h][g-1][i] += dp[k][j][h][g][i];
                     } else if(array[k+1] == '>'){
                        if(i > 0) dp[k+1][j][h][g][i-1] += dp[k][j][h][g][i];
                     }
                     
                  }
               }
            }
         }
      }
      
      long answer = dp[n-1][0][0][0][0];
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}