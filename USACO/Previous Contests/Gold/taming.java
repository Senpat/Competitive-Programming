/*
TASK: taming
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class taming{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("taming.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      int[][][] dp = new int[n+1][n+1][n+1];
      for(int k = 0; k <= n; k++) 
         for(int j = 0; j <= n; j++) Arrays.fill(dp[k][j],Integer.MAX_VALUE);
      
      int ori = 0;
      if(array[0]!=0) ori = 1;
      
      dp[1][1][0] = ori;
      
      for(int k = 1; k < n; k++){
         for(int j = 1; j <= n; j++){
            for(int h = 0; h <= n; h++){
               if(dp[k][j][h]<Integer.MAX_VALUE){
               //add new breakout
                  int i = 1;
                  if(array[k]==0) i = 0;
                  if(in(dp,k+1,j+1,h)) dp[k+1][j+1][0] = Math.min(dp[k+1][j+1][0],dp[k][j][h] + i);
               //no new breakout
                  i = 1;
                  if(array[k]==h+1) i = 0;
                  if(in(dp,k+1,j,h+1)) dp[k+1][j][h+1] = Math.min(dp[k+1][j][h+1],dp[k][j][h] + i);
               }
            }
         }
      }
      
      for(int k = 1; k <= n; k++){
         int min = Integer.MAX_VALUE;
         for(int j = 0; j <= n; j++){
            min = Math.min(min,dp[n][k][j]);
         }
         //System.out.println(min);
         out.println(min);
      }
               
      
      
      
      out.close();
   }
   
   public static boolean in(int[][][] d, int a, int b, int c){
      return a<d.length && b<d[0].length && c<d[0][0].length;
   }
}