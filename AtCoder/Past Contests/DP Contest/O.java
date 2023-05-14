//make sure to make new file!
import java.io.*;
import java.util.*;

public class O{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] board = new int[n][n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      //precompute list of masks with each bitcount
      int pn = 1 << n;
      int M = 400000;                           //21 choose 10 = 352716
      int[][] getmasks = new int[n+1][M];         //getmasks[x] stores all masks with x bits
      int[][] maski = new int[pn][2];           //maski[mask] stores # of bits and index in getmasks[# of bits]
      
      int[] getmaski = new int[n+1];
      
      for(int mask = 0; mask < pn; mask++){
         int bits = 0;
         int i = mask;
         while(i > 0){
            if((i & 1) == 1) bits++;
            i >>= 1;
         }
         
         getmasks[bits][getmaski[bits]] = mask;
         maski[mask][0] = bits;
         maski[mask][1] = getmaski[bits];
         
         getmaski[bits]++;
      }
      
      long[][] dp = new long[n+1][pn];         //dp[x][y] = # of ways to pair first x men with women in that subset
      
      dp[0][0] = 1;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < getmaski[k]; j++){
            if(dp[k][j] == 0) continue;
            
            for(int next = 0; next < n; next++){
               if(board[k][next] == 1 && (getmasks[k][j] & (1 << next)) == 0){
                  int nextmaski = maski[getmasks[k][j] | (1 << next)][1];
                  dp[k+1][nextmaski] = (dp[k+1][nextmaski] + dp[k][j] + MOD)%MOD;
               }
            }
         }
      }
      
      long answer = dp[n][0];
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}