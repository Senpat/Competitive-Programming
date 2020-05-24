/*
TASK: sprinklers2
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class sprinklers2{
   
   public static long MOD = 1000000007L;
   
   public static long[][] bits;
   public static int n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("sprinklers2.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprinklers2.out")));
      
      
      n = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[n][n];
      
      for(int k = 0; k < n; k++){
         char[] c = f.readLine().toCharArray();
         for(int j = 0; j < n; j++){
            if(c[j] == '.'){
               matrix[k][j] = 1;
            } else {
               matrix[k][j] = 0;
            }
         }
      }
      
      int[][] psums = new int[n][n+1];
      for(int k = 0; k < n; k++){
         psums[k][0] = 0;
         for(int j = 1; j <= n; j++){
            psums[k][j] = psums[k][j-1]+matrix[k][j-1];
         }
      }
      
      
      long[] pow2 = new long[2005];
      pow2[0] = 1L;
      
      for(int k = 1; k < 2005; k++){
         pow2[k] = (pow2[k-1]*2L+MOD)%MOD;
      }
      
      bits = new long[n][n+2];
      
      long[][] dp = new long[n][n+1];
      
      for(int k = 0; k <= n; k++){
         
         int sum = psums[0][n];
         if(k<n && matrix[0][k] == 1) sum--;
         if(k>0 && matrix[0][k-1] == 1) sum--;
         
         dp[0][k] = pow2[sum];
         if(k>0 && matrix[0][k-1] == 0){
            dp[0][k] = 0L;
         }
         
         if(k<n && matrix[0][k] == 1){
            update(k+1,dp[0][k],0);
         }
      }
      
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j <= n; j++){
            if(j == 0){
               dp[k][j] = (dp[k-1][j]*pow2[psums[k][n]]+MOD)%MOD;
               if(k==1 && matrix[0][0] == 0){
                  dp[k][j] = (dp[k-1][j]*pow2[Math.max(0,psums[k][n]-1)]+MOD)%MOD;
               }
            } else {
               //check up
               long sum = 0L;
               int numspaces = psums[k][n];
               if(j<n && matrix[k][j]==1) numspaces--;
               if(j<n && matrix[k-1][j]==1) numspaces++;
               sum = (pow2[numspaces]*dp[k-1][j]+MOD)%MOD;
               
               //across
               int num = psums[k][n];
               if(j<n && matrix[k][j] == 1) num--;
               if(j>0 && matrix[k][j-1] == 1) num--;
               
               if(matrix[k][j-1] == 1){
                  //System.out.println(psum(j,k-1));
                  long add = (pow2[num]*psum(j,k-1)+MOD)%MOD;
                  sum = (sum + add + MOD)%MOD;
               }
               
               dp[k][j] = sum;
            }
            
            if(j<n && matrix[k][j]==1){
               update(j+1,dp[k][j],k);
            }
         }
      }
      
      long answer = 0L;
      for(int k = 0; k <= n; k++){
         if(k<n && matrix[n-1][k]==0) continue;
         answer = (answer + dp[n-1][k] + MOD)%MOD;
      }
      
      System.out.println(answer);
      out.println(answer);
               
            
      
      
      
        
        
      out.close();
   }
   
   
   public static void update(int i, long x, int z){
      for(; i <= n+1; i += i&-i){
         //System.out.println(i);
         bits[z][i]=(bits[z][i]+x+MOD)%MOD;
      }
   
   }
   
   public static long psum(int i, int z){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum = (sum + bits[z][i]+MOD)%MOD;
      }
      return sum;
   
   }
      
}