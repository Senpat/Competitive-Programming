//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2229{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[][] psum = new long[2][m+2];
      for(int k = 1; k <= m+1; k++){
         psum[1][k] = 1L;
      }
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j <= m; j++){
            //if(dp[k][j] == 0) continue;
            int i = k&1;
            int i2 = i^1;
            
            int r = j;
            int l = Math.max(0,j-k);
            
            long cur = psum[i][r+1] - psum[i][l];
            if(cur < 0) cur += MOD;
            psum[i2][j+1] = psum[i2][j] + cur;
            if(psum[i2][j+1] >= MOD) psum[i2][j+1] -= MOD;
         }
      }
      
      long answer = ((psum[n&1][m+1] - psum[n&1][m])%MOD + MOD)%MOD;
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}