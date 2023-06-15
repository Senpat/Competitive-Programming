//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1637{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int x = Integer.parseInt(f.readLine());
      
      int[] dp = new int[x+1];
      Arrays.fill(dp,Integer.MAX_VALUE);
      dp[x] = 0;
      for(int k = x; k > 0; k--){
         if(dp[k] == Integer.MAX_VALUE) continue;
         int i = k;
         while(i > 0){
            int m10 = i%10;
            if(m10 > 0){
               dp[k-m10] = Math.min(dp[k-m10],dp[k]+1);
            }
            i /= 10;
         }
      }
      
      out.println(dp[0]);
      
      
      
      
      
      out.close();
   }
   
      
}