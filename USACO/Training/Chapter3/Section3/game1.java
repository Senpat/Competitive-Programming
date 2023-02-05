/*
TASK: game1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class game1{

   public static int[] array;
   public static int[][][] dp;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("game1.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      array = new int[n];
      
      int i = 0;
      while(true){
         String s = f.readLine();
         if(s == null) break;
         
         StringTokenizer st = new StringTokenizer(s);
         
         while(st.hasMoreTokens()){
            array[i] = Integer.parseInt(st.nextToken());
            i++;
         }
      }
      
      dp = new int[n][n][2];
      //0 score for player's whose turn it is
      //1 score for other player
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            dp[k][j][0] = -1;
            dp[k][j][1] = -1;
         }
      }
      
      for(int k = 0; k < n; k++){
         dp[k][k][0] = array[k];
         dp[k][k][1] = 0;
      }
      
      calc(0,n-1);
      
      out.println(dp[0][n-1][0] + " " + dp[0][n-1][1]);
      
        
      out.close();
   }
   
   public static void calc(int l, int r){
      if(dp[l][r][0] != -1) return;
      
      calc(l,r-1);
      calc(l+1,r);
      
      int left = array[l] + dp[l+1][r][1];
      int right = dp[l][r-1][1] + array[r];
      
      if(left > right){
         dp[l][r][0] = left;
         dp[l][r][1] = dp[l+1][r][0];
      } else {
         dp[l][r][0] = right;
         dp[l][r][1] = dp[l][r-1][0];
      }
   }
      
}