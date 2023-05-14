//make sure to make new file!
import java.io.*;
import java.util.*;
//fail
public class E146{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      n--;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[][] dp = new long[n][2]; //initial dp
      int[][] parent = new long[n][2];
      for(int k = 0; k < n; k++){
         dp[k][0] = Long.MAX_VALUE/2;
         dp[k][1] = Long.MAX_VALUE/2;
         parent[k][0] = -1;
         parent[k][1] = -1;
      }
      //dp[x][0] -> min cost of getting no adjacent empty for first x elements, and x is not used
      //dp[x][1] -> min cost of getting no adjacent empty for first x elements, and x is used
      
      //first element must be used
      dp[0][1] = array[0];
      
      for(int k = 1; k < n; k++){
         //fill unused
         if(dp[k-1][1] < dp[k][0]){
            parent[k][0] = 1;
            dp[k][0] = dp[k-1][1];
         }
         
         //fill used
         long minnext = Math.min(dp[k-1][0],dp[k-1][1]);
         if(minnext < dp[k][1]){
            dp[k][1] = minnext + array[k];
            if(dp[k-1][0] < dp[k-1][1]) parent[k][1] = 0;
            else parent[k][1] = 1;  
         }
      }
      
      //get nodes that are used
      boolean[] used = new boolean[n];
      int p = 1;
      for(int k = n-1; k >= 0; k--){
         if(p == 1) used = true;
         p = parent[k][p];
      }
      
      long answer = dp[n-1][1];
      
      int t = Integer.parseInt(st.nextToken());
      for(int q = 0; q < t; q++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken())-1;
         long x = Long.parseLong(st.nextToken());
         
         if(i == 0 || i == n-1){
            answer += x-array[i];
         } else if(used[i]){
            if(x <= array[i]){
               answer += x-array[i];
            } else {
               //increase, check if you still want to use it
               //can get rid of it but you would need to add i-1 and i+1
               long newanswer = 
            }
         } else {
            if(x >= array[i]){
               //don't do anything, continue to ignore
            } else {
               //decrease, see if you want to add it
               long newanswer = answer;
               
            }
         }
         
         array[i] = x;
         
         out.println(2*answer);
      } 
      
      
      
      
      out.close();
   }
   
      
}