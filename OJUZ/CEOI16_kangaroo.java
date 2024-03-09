//make sure to make new file!
import java.io.*;
import java.util.*;
//connected components dp practice
public class CEOI16_kangaroo{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int cs = Integer.parseInt(st.nextToken())-1;
      int cf = Integer.parseInt(st.nextToken())-1;
      
      //dp[x][y] -> # of ways to make the # of jump of first x indeces, such that there are y components formed
      //ex: jumps 2 -> 5 -> 1 -> 4 -> 3, make components starting from the 1
      //no component can ever be like 123 or 321, where 2nd < 1st or 2nd to last > last
      //going back and forth means you can't have a_(x-1) < a_x < a_(x+1) and vice versa
      //if not start/end, can never have a_(x-1) < a_x, because the next element is guaranteed t
      long[][] dp = new long[n][n+1];
      
      dp[0][1] = 1L;
      
      int start = 1;
      int end = 1;
      
      for(int k = 0; k < n-1; k++){
         if(k == cs) start--;
         if(k == cf) end--;
         
         for(int j = 1; j <= n; j++){
            if(dp[k][j] == 0L) continue;
            
            if(k+1 == cs || k+1 == cf){
               //make new component at beginning/end
               dp[k+1][j+1] = (dp[k+1][j+1] + dp[k][j] + MOD)%MOD;
               
               //add to first/last component
               dp[k+1][j] = (dp[k+1][j] + dp[k][j] + MOD)%MOD;
            } else {
               //make new component
               dp[k+1][j+1] = (dp[k+1][j+1] + dp[k][j] * (long)(j-1 + start + end) + MOD)%MOD;
               
               //join components
               if(j > 1){
                  dp[k+1][j-1] = (dp[k+1][j-1] + dp[k][j] * (long)(j-1) + MOD)%MOD;
               }
            }
         }
      }
      
      /*
      for(int k = 0; k < n; k++){
         for(int j = 1; j <= n; j++){
            out.print(dp[k][j] + " ");
         }
         out.println();
      }*/
      
      out.println(dp[n-1][1]);
      
      
      
      
      
      out.close();
   }
   
      
}