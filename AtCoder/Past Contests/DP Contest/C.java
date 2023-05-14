//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] add = new int[n][3];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         add[k][0] = a;
         add[k][1] = b;
         add[k][2] = c;
      }
      
      int[][] dp = new int[n][3];      //last number
      for(int k = 0; k < n; k++){
         for(int j = 0; j < 3; j++){
            dp[k][j] = -1;
         }
      }
      
      dp[0][0] = add[0][0];
      dp[0][1] = add[0][1];
      dp[0][2] = add[0][2];
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < 3; j++){
            for(int h = 0; h < 3; h++){
               if(dp[k][j] != -1){
                  if(h != j){
                     dp[k+1][h] = Math.max(dp[k+1][h],dp[k][j] + add[k+1][h]);
                  }     
               }
            }
         }
      }
      
      int answer = 0;
      for(int k = 0; k < 3; k++){
         answer = Math.max(answer,dp[n-1][k]);
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}