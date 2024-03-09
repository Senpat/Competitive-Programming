//make sure to make new file!
import java.io.*;
import java.util.*;
//div2D
public class D901D2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] freq = new int[n+5];
         for(int k = 0; k < n; k++){
            int i = Integer.parseInt(st.nextToken());
            if(i <= n){
               freq[i]++;
            }
         }
         
         int mex = 0;
         while(freq[mex] > 0) mex++;
         
         long[] dp = new long[mex+1];
         Arrays.fill(dp,Long.MAX_VALUE);
         dp[mex] = 0L;
         
         for(int k = mex-1; k >= 0; k--){
            for(int j = k+1; j <= mex; j++){
               //mex before you start deleting this value
               dp[k] = Math.min(dp[k], dp[j] + (freq[k]-1) * j + k);
            }
         }
         
         out.println(dp[0]);
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}