/*
TASK: checklist
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class checklist{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("checklist.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int h = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      
      int[] hx = new int[h];
      int[] hy = new int[h];
      
      
      int[] gx = new int[g];
      int[] gy = new int[g];
      
      for(int k = 0; k < h; k++){
         st = new StringTokenizer(f.readLine());
         
         hx[k] = Integer.parseInt(st.nextToken());
         hy[k] = Integer.parseInt(st.nextToken());
      }
      
      
      for(int k = 0; k < g; k++){
         st = new StringTokenizer(f.readLine());
         
         gx[k] = Integer.parseInt(st.nextToken());
         gy[k] = Integer.parseInt(st.nextToken());
      }
      
      long[][][] dp = new long[h+1][g+1][2];          //1 is g, 0 is h
      
      for(int k = 0; k <= h; k++) for (int j = 0; j <= g; j++) for(int l = 0; l < 2; l++) dp[k][j][l] = Long.MAX_VALUE;
      
      dp[1][0][0] = 0;
      
      for(int k = 1; k <= h; k++){
         for(int j = 0; j <= g; j++){
            for(int i = 0; i < 2; i++){
               if(dp[k][j][i] == Long.MAX_VALUE) continue;
               
               int curx = i==0 ? hx[k-1] : gx[j-1];
               int cury = i==0 ? hy[k-1] : gy[j-1];
               
               //add to h
               if(k < h) dp[k+1][j][0] = Math.min(dp[k+1][j][0],dp[k][j][i] + (long)Math.pow(Math.abs(curx-hx[k]),2) + (long)Math.pow(Math.abs(cury-hy[k]),2));
               //add to g
               if(j < g) dp[k][j+1][1] = Math.min(dp[k][j+1][1],dp[k][j][i] + (long)Math.pow(Math.abs(curx-gx[j]),2) + (long)Math.pow(Math.abs(cury-gy[j]),2));
            }
         }
      }
      
      System.out.println(dp[h][g][0] + " " + dp[h][g][1]);
      out.println(dp[h][g][0]);
      
      
      out.close();
   }
}