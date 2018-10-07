//Cut Ribbon
import java.io.*;
import java.util.*;

public class A189{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      int[] dp = new int[n+a+b+c];
      
      dp[0] = 1;
      
      for(int k = 0; k < n; k++){
         if(dp[k]>0){
            dp[k+a] = Math.max(dp[k+a],dp[k]+1);
            dp[k+b] = Math.max(dp[k+b],dp[k]+1);
            dp[k+c] = Math.max(dp[k+c],dp[k]+1);
         }
      }
      
      out.println(dp[n]-1);
      
      out.close();
   }
   
}