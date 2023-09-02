//make sure to make new file!
import java.io.*;
import java.util.*;

public class CCTON{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] last = new int[n+1];
         Arrays.fill(last,-1);
         int[] next = new int[n];
         Arrays.fill(next,-1);
         for(int k = n-1; k >= 0; k--){
            if(last[array[k]] != -1){
               next[k] = last[array[k]];
            }
            last[array[k]] = k;
         }
         
         //dp[x][y] = max # of elements you can delete in first x elements
         //y = 0 -> element x is not deleted
         //y = 1 -> element x is deleted (you can jump to next x)
         int[][] dp = new int[n][2];
         
         for(int k = 0; k < n-1; k++){
            //delete k
            if(next[k] != -1){
               dp[next[k]][1] = Math.max(dp[next[k]][1],dp[k][0] + next[k]-k+1);
               dp[next[k]][1] = Math.max(dp[next[k]][1],dp[k][1] + next[k]-k);
            }
            
            //don't delete
            dp[k+1][0] = Math.max(dp[k+1][0],Math.max(dp[k][0],dp[k][1]));
         }
         
         int answer = Math.max(dp[n-1][0],dp[n-1][1]);
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}