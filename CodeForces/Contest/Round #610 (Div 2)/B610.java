//make sure to make new file!
import java.io.*;
import java.util.*;

public class B610{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long p = Long.parseLong(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         Long[] array = new Long[n];
         st = new StringTokenizer(f.readLine());
         
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         long[] dp = new long[n];
         int answer = 0;
         for(int k = 0; k < n; k++){
            if(k < m-1){
               dp[k] = array[k] + (k == 0 ? 0 : dp[k-1]);
            } else if(k==m-1){
               dp[k] = array[k]; 
            } else {
               dp[k] = array[k] + dp[k-m];
            }
            
            if(dp[k] <= p){
               answer = Math.max(answer,k+1);
            }
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}