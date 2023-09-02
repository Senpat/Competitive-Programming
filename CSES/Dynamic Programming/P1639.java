//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1639{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      int an = a.length;
      int bn = b.length;
      
      //minimum # to get everything in front of x and y equal (x and y are not necessarily equal)
      int[][] dp = new int[an+1][bn+1];
      for(int k = 0; k <= an; k++){
         for(int j = 0; j <= bn; j++){
            dp[k][j] = Integer.MAX_VALUE;
         }
      }
      
      dp[0][0] = 0;
      
      for(int k = 0; k <= an; k++){
         for(int j = 0; j <= bn; j++){
            if(k == an && j == bn) continue;
            //can only remove
            if(k == an){
               dp[k][j+1] = Math.min(dp[k][j+1],dp[k][j]+1);
               continue;
            }
            if(j == bn){
               dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j]+1);
               continue;
            }
            //do nothing
            if(a[k] == b[j]){
               dp[k+1][j+1] = Math.min(dp[k+1][j+1],dp[k][j]);
            }
            
            //remove a
            dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j]+1);
            //remove b
            dp[k][j+1] = Math.min(dp[k][j+1],dp[k][j]+1);
            
            //replace a to b (or b to a)
            dp[k+1][j+1] = Math.min(dp[k+1][j+1],dp[k][j]+1);
         }
      }
      
      int answer = dp[an][bn];
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}