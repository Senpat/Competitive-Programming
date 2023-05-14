//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      
      int an = a.length;
      int bn = b.length;
      
      int[][] dp = new int[an+1][bn+1];
      int[][][] par = new int[an+1][bn+1][2];            //a parent, b parent
      
      for(int k = 1; k <= an; k++){
         for(int j = 1; j <= bn; j++){
            if(dp[k-1][j] > dp[k][j-1]){
               dp[k][j] = dp[k-1][j];
               par[k][j][0] = k-1;
               par[k][j][1] = j;
            } else {
               dp[k][j] = dp[k][j-1];
               par[k][j][0] = k;
               par[k][j][1] = j-1;
            }
            
            if(a[k-1] == b[j-1]){
               if(dp[k-1][j-1]+1 > dp[k][j]){
                  dp[k][j] = dp[k-1][j-1]+1;
                  par[k][j][0] = k-1;
                  par[k][j][1] = j-1;
               }
            }  
         }
      }
      
      int ai = an;
      int bi = bn;
      
      StringBuilder sb = new StringBuilder();
      
      while(ai > 0 || bi > 0){
         if(par[ai][bi][0] == ai-1 && par[ai][bi][1] == bi-1){
            sb.append(a[ai-1]);
         }
         int nexta = par[ai][bi][0];
         int nextb = par[ai][bi][1];
         
         ai = nexta;
         bi = nextb;
      }
      
      out.println(sb.reverse());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}