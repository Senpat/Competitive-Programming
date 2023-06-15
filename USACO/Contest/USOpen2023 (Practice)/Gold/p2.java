//make sure to make new file!
import java.io.*;
import java.util.*;

public class p2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      int[] cost = new int[n];
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         cost[k] = Integer.parseInt(st.nextToken());
      }
      
      String bessie = "bessie";
      
      //dp[x][y] stores # of full words you have and min cost,
      //using first x letters and the next letter is bessie.charAt(y)
      int[][][] dp = new int[n][6][2];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < 6; j++){
            dp[k][j][0] = 0;
            dp[k][j][1] = Integer.MAX_VALUE;
         }
      }
      
      //initial
      //keep first letter that's not b (ever optimal to delete)
      dp[0][0][0] = 0;
      dp[0][0][1] = 0;
      //keep first letter and add
      if(array[0] == 'b'){
         dp[0][1][0] = 0;
         dp[0][1][1] = 0;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < 6; j++){
            if(dp[k][j][1] == Integer.MAX_VALUE) continue;
            
            //delete k+1
            if(dp[k][j][0] > dp[k+1][j][0]){
               dp[k+1][j][0] = dp[k][j][0];
               dp[k+1][j][1] = dp[k][j][1] + cost[k+1];
            } else if(dp[k][j][0] == dp[k+1][j][0]){
               dp[k+1][j][1] = Math.min(dp[k+1][j][1],dp[k][j][1] + cost[k+1]);
            }
            
            //keep and add to a bessie
            if(array[k+1] == bessie.charAt(j)){
               if(j == 5){
                  if(dp[k][j][0]+1 > dp[k+1][0][0]){
                     dp[k+1][0][0] = dp[k][j][0]+1;
                     dp[k+1][0][1] = dp[k][j][1];
                  } else if(dp[k][j][0]+1 == dp[k+1][0][0]){
                     dp[k+1][0][1] = Math.min(dp[k+1][0][1],dp[k][j][1]);
                  }
               } else {
                  if(dp[k][j][0] > dp[k+1][j+1][0]){
                     dp[k+1][j+1][0] = dp[k][j][0];
                     dp[k+1][j+1][1] = dp[k][j][1];
                  } else if(dp[k][j][0] == dp[k+1][j+1][0]){
                     dp[k+1][j+1][1] = Math.min(dp[k+1][j+1][1],dp[k][j][1]);
                  }
               }
            }
            
            //keep and restart
            if(dp[k][j][0] > dp[k+1][0][0]){
               dp[k+1][0][0] = dp[k][j][0];
               dp[k+1][0][1] = dp[k][j][1];
            } else if(dp[k][j][0] == dp[k+1][0][0]){
               dp[k+1][0][1] = Math.min(dp[k+1][0][1],dp[k][j][1]);
            }
         }
      }
      
      int maxbessie = -1;
      int mincost = Integer.MAX_VALUE;
      
      for(int k = 0; k < 6; k++){
         if(dp[n-1][k][0] > maxbessie){
            maxbessie = dp[n-1][k][0];
            mincost = dp[n-1][k][1];
         } else if(dp[n-1][k][0] == maxbessie){
            mincost = Math.min(mincost,dp[n-1][k][1]);
         }
      }
      
      if(maxbessie == 0){
         mincost = 0;
      }
      
      out.println(maxbessie);
      out.println(mincost);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}