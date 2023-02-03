//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class C533b{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int l = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      
      long[] mods = new long[3];
      
      if(r-l < 10){
         for(int k = l; k <= r; k++){
            mods[k%3]++;
         }
      } else {
         for(int k = l%3; k <= 2; k++){
            mods[k]++;
         }
         for(int k = 0; k <= r%3; k++){
            mods[k]++;
         }
         l += 3 - l%3;
         r -= r%3;
         
         mods[0] += (r-l)/3;
         mods[1] += (r-l)/3;
         mods[2] += (r-l)/3;
      
      }
      
      long[][] dp = new long[n][3];
      dp[0][0] = mods[0];
      dp[0][1] = mods[1];
      dp[0][2] = mods[2];
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < 3; j++){
            for(int h = 0; h < 3; h++){
               dp[k+1][(h+j)%3] = (dp[k+1][(h+j)%3] + dp[k][j]*mods[h] + MOD)%MOD;
            }
         }
      }
      
      out.println(dp[n-1][0]);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}