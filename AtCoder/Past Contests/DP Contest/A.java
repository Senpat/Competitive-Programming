//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] dp = new int[n];
      Arrays.fill(dp,Integer.MAX_VALUE);
      dp[0] = 0;
      
      for(int k = 0; k < n-1; k++){
         if(k+1 < n){
            dp[k+1] = Math.min(dp[k+1],dp[k] + Math.abs(array[k+1]-array[k]));
         }
         
         if(k+2 < n){
            dp[k+2] = Math.min(dp[k+2],dp[k] + Math.abs(array[k+2]-array[k]));
         }
      }
      
      out.println(dp[n-1]);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}