//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1744{
   
   public static int[][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      //force x <= y
      if(x > y){
         int temp = x;
         x = y;
         y = temp;
      }
      
      int N = 500;
      dp = new int[N+1][N+1];
      for(int k = 0; k <= 500; k++){
         Arrays.fill(dp[k],Integer.MAX_VALUE);
      }
      
      dothing(x,y);
      
      out.println(dp[x][y]);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int dothing(int x, int y){
      if(x > y){
         return dothing(y,x);
      }
      if(x == y) {
         dp[x][y] = 0;
         return 0;
      }
      if(dp[x][y] != Integer.MAX_VALUE){
         return dp[x][y];
      }
      
      int answer = Integer.MAX_VALUE;
      for(int k = 1; k*2 <= x; k++){
         answer = Math.min(answer, dothing(k,y) + dothing(x-k,y) + 1);
      } 
      for(int k = 1; k*2 <= y; k++){
         answer = Math.min(answer, dothing(x,k) + dothing(x,y-k) + 1);
      }
      
      dp[x][y] = answer;
      return answer;
   }
      
}