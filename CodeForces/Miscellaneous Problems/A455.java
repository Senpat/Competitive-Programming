//Boredom
//L
import java.io.*;
import java.util.*;

public class A455{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] dp = new int[100001];
      int[] nums = new int[n];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         nums[k] = i;
         dp[i]+=i;
      }
      
      int max = 0;
      for(int k = 0; k < n; k++){
         max = Math.max(dp[nums[k]-1]+dp[nums[k]+1],max);
      }
      
      out.println(max);
      
      
      
      out.close();
   }
   
}