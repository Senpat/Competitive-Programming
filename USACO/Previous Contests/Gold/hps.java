/*
TASK: hps
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class hps{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("hps.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         String rl = f.readLine();
         if(rl.equals("H")) array[k] = 0;
         if(rl.equals("P")) array[k] = 1;
         if(rl.equals("S")) array[k] = 2;
         
      }
      
      HashMap<Integer,Integer> game = new HashMap<Integer,Integer>();
      
      game.put(2,0);
      game.put(1,2);
      game.put(0,1);
      
      int[][][] dp = new int[n][m+1][3];
      
      dp[0][0][game.get(array[0])] = 1;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= m; j++){
            for(int h = 0; h < 3; h++){
               if(dp[k][j][h] == 0 && j == 0 && h == game.get(array[k])){                  //if currently equals 0, maybe you can start there
                  dp[k][j][h] = 1;
               }
               if(dp[k][j][h] == 0) continue;
               if(h == game.get(array[k+1])){                  //only not switch
                  dp[k+1][j][h] = Math.max(dp[k+1][j][h],dp[k][j][h]+1);
               } else {
                  dp[k+1][j][h] = Math.max(dp[k+1][j][h],dp[k][j][h]);            //take the L and not switch
                  if(j<m) dp[k+1][j+1][game.get(array[k+1])] = Math.max(dp[k+1][j+1][game.get(array[k+1])],dp[k][j][h]+1);       //switch and take the dub
               }
               
            }
         }
      }
      
      int max = 0;
      
      for(int j = 0; j <= m; j++){
         for(int h = 0; h < 3; h++){
            max = Math.max(max,dp[n-1][j][h]);
         
         }
      }
      System.out.println(max);
      out.println(max);
      
      
      out.close();
   }
}