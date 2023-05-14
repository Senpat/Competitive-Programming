//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] dp = new int[n];
      Arrays.fill(dp,Integer.MAX_VALUE);
      dp[0] = 0;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 1; j <= m; j++){
            if(k+j < n){
               dp[k+j] = Math.min(dp[k+j],dp[k] + Math.abs(array[k+j]-array[k]));
            }
         }
      }
      
      out.println(dp[n-1]);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}