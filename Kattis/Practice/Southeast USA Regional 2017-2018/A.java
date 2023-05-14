//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      char[] array = f.readLine().toCharArray();
      
      int n = array.length;
      
      if(m > n || x > n){
         out.println(-1);
         out.close();
         return;
      }
      
      int[] numflips = new int[n];        //# of flips needed to make the next m elements a D
      Arrays.fill(numflips,-1);
      for(int k = 0; k+m-1 < n; k++){
         numflips[k] = 0;
         if(array[k] == 'G') numflips[k]++;
         for(int j = 1; j < m; j++){
            if(array[k+j] == 'G' && array[k+j-1] == 'D') numflips[k]++;
         }
      }
      
      //dp[x][y][z] is min # of flips for the first x elements to have y sequences
      //z: 0 -> doesn't end in flip, 1 -> ends in flip
      int[][][] dp = new int[n][n+1][2];       
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j <= n; j++){
            dp[k][j][0] = Integer.MAX_VALUE;
            dp[k][j][1] = Integer.MAX_VALUE;
         }
      }
      
      //start with flip
      dp[0][0][1] = 1;
      //start without flip
      dp[0][0][0] = 0;
      
      //start with sequence
      int end = array[m-1] == 'D' ? 0 : 1;
      dp[m-1][1][end] = numflips[0];
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= n; j++){
            if(dp[k][j][0] != Integer.MAX_VALUE){
               //start new flip
               dp[k+1][j][1] = Math.min(dp[k+1][j][1],dp[k][j][0]+1);
               //continue no flip
               dp[k+1][j][0] = Math.min(dp[k+1][j][0],dp[k][j][0]);
               
               //make new sequence
               if(k+m < n && array[k] == 'G'){
                  end = array[k+m] == 'D' ? 0 : 1;
                  dp[k+m][j+1][end] = Math.min(dp[k+m][j+1][end],dp[k][j][0] + numflips[k+1]);
               }
            }
            
            if(dp[k][j][1] != Integer.MAX_VALUE){
               //end flip
               dp[k+1][j][0] = Math.min(dp[k+1][j][0],dp[k][j][1]);
               //continue flip
               dp[k+1][j][1] = Math.min(dp[k+1][j][1],dp[k][j][1]);
               
               //make new sequence
               if(k+m < n && array[k] == 'D'){
                  int cancel = array[k+1] == 'G' ? 1 : 0;
                  end = array[k+m] == 'D' ? 0 : 1;
                  dp[k+m][j+1][end] = Math.min(dp[k+m][j+1][end],dp[k][j][1] + numflips[k+1] - cancel);
               }
            }
         }
      }
      
      long answer = Integer.MAX_VALUE;
      for(int k = x; k <= n; k++){
         answer = Math.min(answer,Math.min(dp[n-1][x][0],dp[n-1][x][1]));
      }
      
      if(answer == Integer.MAX_VALUE){
         out.println(-1);
      } else {
         out.println(answer);
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}