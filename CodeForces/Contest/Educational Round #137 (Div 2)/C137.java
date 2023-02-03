//make sure to make new file!
import java.io.*;
import java.util.*;

public class C137{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] lidsch = f.readLine().toCharArray();
         int[] lids = new int[n];
         for(int k = 0; k < n; k++){
            lids[k] = Character.getNumericValue(lidsch[k]);
         }
         
         int[] array = new int[n];
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         //[x][1] -> x used (x+1)th lid, [x][0] -> x used x or below lid
         int[][] dp = new int[n][2];
         for(int k = 0; k < n; k++) Arrays.fill(dp[k],0);
         
         
         if(lids[0] == 1){
            dp[0][0] = array[0];
         }
         if(n >= 2 && lids[1] == 1){
            dp[0][1] = array[0];
         }
         
         for(int k = 1; k < n; k++){
            //don't use
            dp[k][0] = Math.max(dp[k][0],dp[k-1][0]);
            dp[k][0] = Math.max(dp[k][0],dp[k-1][1]);
            
            if(lids[k] == 1){
               dp[k][0] = Math.max(dp[k][0],dp[k-1][0]+array[k]);
            }
            
            if(k < n-1 && lids[k+1] == 1){
               dp[k][1] = Math.max(dp[k][1],dp[k-1][0]+array[k]);
               dp[k][1] = Math.max(dp[k][1],dp[k-1][1]+array[k]);
            }
         }
         
         int answer = Math.max(dp[n-1][0],dp[n-1][1]);
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}