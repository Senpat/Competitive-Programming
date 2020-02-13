//Erase Subsequences
import java.io.*;
import java.util.*;

public class E1303{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         char[] a = f.readLine().toCharArray();
         char[] b = f.readLine().toCharArray();
         int n = a.length;
         int nb = b.length;
         
         boolean found = false;
         
         for(int s = 1; s <= nb; s++){
            //split, first s points are in first part, next t-s are in second part.
            
            int[][] dp = new int[n+1][s+1];
            
            for(int k = 0; k <= n; k++){
               Arrays.fill(dp[k],-1);
            }
            
            dp[0][0] = 0;
            
            for(int k = 0; k < n; k++){
               for(int j = 0; j <= s; j++){
                  if(dp[k][j] == -1) continue;
                  if(j < s && a[k] == b[j]){
                     dp[k+1][j+1] = Math.max(dp[k+1][j+1],dp[k][j]);
                  }
                  if(s+dp[k][j] < nb && a[k] == b[s+dp[k][j]]){
                     dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j]+1);
                  }
                  dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j]);
               }
            }
            
            if(dp[n][s] == nb-s){
               found = true;
               break;
            }
         }
         
         if(found){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
   
      
      
      
      
      
      out.close();
   }
   
      
}