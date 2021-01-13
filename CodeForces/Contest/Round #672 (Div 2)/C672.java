//make sure to make new file!
import java.io.*;
import java.util.*;
//solves first version
public class C672{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long[][] dp = new long[n][2];         //0 is last number is positive, 1 is last number is negative
         dp[0][0] = array[0];
         
         for(int k = 1; k < n; k++){
            //add array[k]
            dp[k][0] = Math.max(dp[k-1][0],dp[k-1][1] + array[k]);
            //subtract array[k]
            dp[k][1] = Math.max(dp[k-1][1],dp[k-1][0] - array[k]);
         }
         
         out.println(dp[n-1][0]);
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}