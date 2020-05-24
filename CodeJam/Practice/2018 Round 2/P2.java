//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      
      int MAX = 1000;         //600 also works
      int MAXRB = 500;
      int[] x = new int[MAX];
      int[] y = new int[MAX];
      
      int i = 0;
      int sum = 1;
      
      while(i < MAX){
         int k;
         for(k = 0; i+k < MAX && k <= sum; k++){
            x[k+i] = k;
            y[k+i] = sum-k;
         }  
         sum++;
         i+=sum;
         
      }
         
      
      int[][] maxes = new int[MAXRB+1][MAXRB+1];
      for(int k = 0; k <= MAXRB; k++){
         for(int j = 0; j <= MAXRB; j++){
            maxes[k][j] = -1;
         }
      }
         
      int[][] dp1 = new int[MAXRB+1][MAXRB+1];
         
      for(int k = 0; k <= MAXRB; k++){
         for(int j = 0; j <= MAXRB; j++){
            dp1[k][j] = -1;
            
         }
      }
      
      int[][] dp2 = new int[MAXRB+1][MAXRB+1];
         
      for(int k = 0; k <= MAXRB; k++){
         for(int j = 0; j <= MAXRB; j++){
            dp2[k][j] = -1;
            
         }
      }
         
      dp1[MAXRB][MAXRB] = 0;
      maxes[MAXRB][MAXRB] = 0;
      
      for(int h = 0; h < MAX; h++){
         for(int k = MAXRB; k >= 0; k--){
            for(int j = MAXRB; j >= 0; j--){
               if(dp1[k][j] == -1) 
                  continue;
               //don't do it
               dp2[k][j] = Math.max(dp2[k][j],dp1[k][j]);
               
               
               //do it
               if(k >= x[h] && j >= y[h]){
                  dp2[k-x[h]][j-y[h]] = Math.max(dp2[k-x[h]][j-y[h]],dp1[k][j]+1);
               }
               
               
               
            }
         }
        
        //move dp2 to dp1
         for(int k = 0; k <= MAXRB; k++){
            for(int j = 0; j <= MAXRB; j++){
               dp1[k][j] = dp2[k][j];
               maxes[k][j] = Math.max(maxes[k][j],dp2[k][j]);
               dp2[k][j] = -1;                                    //reset dp2
            }
         }   
         
        
        
      }
      
      
      /*
      for(int k = r; k >= 0; k--){
         for(int j = b; j >= 0; j--){
            for(int h = 0; h < MAX; h++){
               if(dp[k][j][h] == -1) 
                  continue;
                  
                  //don't do it
               dp[k][j][h+1] = Math.max(dp[k][j][h+1],dp[k][j][h]);
                  
                  
                  //do it
               if(k >= x[h] && j >= y[h]){
                  dp[k-x[h]][j-y[h]][h+1] = Math.max(dp[k-x[h]][j-y[h]][h+1],dp[k][j][h]+1);
               }
            }
         }
      }
      
      */
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int r = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         
         int answer = 0;
         for(int k = MAXRB; k >= MAXRB - r; k--){
            for(int j = MAXRB; j >= MAXRB - b; j--){
               answer = Math.max(answer,maxes[k][j]);
            }
         }
         
         out.println("Case #" + q + ": " + answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}