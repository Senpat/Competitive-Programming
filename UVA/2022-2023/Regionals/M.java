import java.io.*;
import java.util.*;
//alchemy2
//in contest: O(N^2)
//improvement to O(N)
public class M {
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
   
      char[] s = f.readLine().toCharArray();
      int sn = s.length;
      
      if(sn == 2){
         if(s[0] == s[1]){
            out.println(0);
         } else {
            out.println(1);
         }
         out.close();
         return;
      }
   
      int n = (sn)/2;
      int[] array = new int[n];
   
      for(int k = 0; k < n; k++){
         if(s[k] == s[sn-k-1]){
            array[k] = 1;
         } else {
            array[k] = 0;
         }
      }
      
      //stores the next number that is a 0
      int[] next = new int[n+1];
      next[n] = n;
      for(int k = n-1; k >= 0; k--){
         if(array[k] == 0) next[k] = k;
         else next[k] = next[k+1];
      }
   
      //dp[x][y] is minimum moves to get to finish everything before n
      int[] dp = new int[n+1];
   
      int INF = Integer.MAX_VALUE/2;
      Arrays.fill(dp,INF);
   
      //start at first element with 0
      int start = next[0];
   
      dp[start] = 0;
      
      for(int k = start; k < n; k++){
         if(dp[k] == INF) 
            continue;
         if(k == n-1){
            dp[k+1] = Math.min(dp[k+1],dp[k]+1);
            continue;
         }
         
         dp[next[k+2]] = Math.min(dp[next[k+2]],dp[k]+2);
      
         dp[k+1] = Math.min(dp[k+1],dp[k]+1);
         
         if(array[k+1] == 0) {
            dp[next[k+2]] = Math.min(dp[next[k+2]],dp[k]+1);
         }
      }
   
      int answer = dp[n];
      out.println(answer);
   
   
      out.close();
   }
}