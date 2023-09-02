//make sure to make new file!
import java.io.*;
import java.util.*;

public class F626{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(array);
      
      //open and close interval trick
      long[][][] dp = new long[n][n+1][m+1];
      //place first one
      //open
      dp[0][1][0] = 1L;
      //close
      dp[0][0][0] = 1L;
      
      for(int k = 0; k+1 < n; k++){
         for(int j = 0; j <= n; j++){
            for(int h = 0; h <= m; h++){
               if(dp[k][j][h] == 0L) continue;
               
               int val = h + j*(array[k+1]-array[k]);
               
               if(val > m) continue;
               //put in new group (open)
               dp[k+1][j+1][val] = (dp[k+1][j+1][val] + dp[k][j][h] + MOD)%MOD;
               
               //put in new group (close)
               dp[k+1][j][val] = (dp[k+1][j][val] + dp[k][j][h] + MOD)%MOD;
               
               if(j > 0){
                  //put in existing group (open)
                  dp[k+1][j][val] = (dp[k+1][j][val] + dp[k][j][h]*(long)j + MOD)%MOD;
                  
                  //put in existing group (close)
                  dp[k+1][j-1][val] = (dp[k+1][j-1][val] + dp[k][j][h]*(long)j + MOD)%MOD;
                  
               }
               
                    
            }
         }
      }
         
      long answer = 0L;
      for(int k = 0; k <= m; k++){
         //out.println(dp[n-1][0][k]);
         answer = (answer + dp[n-1][0][k] + MOD)%MOD;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}