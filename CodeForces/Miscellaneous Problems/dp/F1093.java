//Vasya and Array
import java.io.*;
import java.util.*;

public class F1093{

   public static long MOD = 998244353;

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long len = Long.parseLong(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
   
      
      int startind = -1;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(startind == -1 && array[k] == -1) startind = k;
      }
      
      long[][] dp = new long[n][m+1];                 //[n][last number] = num ways for that state
      
      //initialize dp matrix
      /*
      if(array[0] != -1){
         dp[0][array[0]] = 1;
               
         //fill with a streak already started
         for(int g = 1; g < n; g++){
            if(array[g] == array[0]) 
               continue;
            if(array[g] != -1 && array[g] != array[0]) 
               break;
            if(g < n-1 && array[g+1] == array[0]){
            //find length of consecutive sequence - check if placing at g is possible considering set sequcne following it
               int curind = g+1;
               while(curind < n && array[curind] == array[0]){
                  curind++;
               }
            
               if(curind >= n || curind >= len) 
                  break;
               else{
                  dp[g][array[0]] = 1;
               }
            } else {
               dp[g][array[0]] = 1;
            }
         }
      }
      */
      
      if(array[0] != -1){
         dp[0][array[0]] = 1;
      }
      
      //array[0] == -1
      if(array[0] == -1){
         for(int j = 1; j <= m; j++){
            for(int k = 0; k < n && k < len; k++){
               if(array[k] == j) 
                  continue;
               if(array[k] != -1 && array[k] != j) 
                  break;
               if(k < n-1 && array[k+1] == j){
                  //find length of consecutive sequence
                  int curind = k+1;
                  while(curind < n && array[curind] == j){
                     curind++;
                  }
                  
                  if(curind >= n || curind >= len) 
                     break;
                  else{
                     dp[k][j] = 1;
                  }
                  
               } else {
                  dp[k][j] = 1;
               }
            }
         }
         
      }
      
      
      
      for(int k = 0; k < n; k++){
      
         if(array[k] != -1){
            long sum = 0L;
            if(k == 0) sum = 1;
            else{
               for(int h = 1; h <= m; h++){
                  sum += dp[k-1][h];
               }
            }
            sum += MOD;
            sum %= MOD;
               
            dp[k][array[k]] = sum;
               
               //fill with a streak already started
            for(int g = 1; k + g < n; g++){
               if(array[k+g] == array[k]) 
                  continue;
               if(array[k+g] != -1 && array[k+g] != array[k]) 
                  break;
               if(k+g < n-1 && array[k+g+1] == array[k]){
                     //find length of consecutive sequence
                  int curind = k+g+1;
                  while(curind < n && array[curind] == array[k]){
                     curind++;
                  }
                     
                  if(curind >= n || curind >= len-1) 
                     break;
                  else{
                     dp[k+g][array[k]] = (dp[k+g][array[k]] + sum + MOD)%MOD;
                  }
               } else {
                  dp[k+g][array[k]] = (dp[k+g][array[k]] + sum + MOD)%MOD;
               }
            }
            
         }
      
         if(k < n-1 && array[k+1] == -1){
            for(int j = 1; j <= m; j++){
               if(dp[k][j] == 0) 
                  continue;
            
            
            
            
            
               //fill
               for(int h = 1; h <= m; h++){
                  if(h == j) 
                     continue;
                  //loop through all lengths of streak
                  for(int g = 1; k + g < n; g++){
                     if(array[k+g] == h) 
                        continue;
                     if(array[k+g] != -1 && array[k+g] != h) 
                        break;
                     if(k+g < n-1 && array[k+g+1] == j){
                        //find length of consecutive sequence
                        int curind = k+g+1;
                        while(curind < n && array[curind] == h){
                           curind++;
                        }
                        
                        if(curind >= n || curind-k >= len) 
                           break;
                        else{
                           dp[k+g][h] = (dp[k+g][h] + dp[k][j] + MOD) % MOD;
                        }
                     } else {
                        dp[k+g][h] = (dp[k+g][h] + dp[k][j] + MOD) % MOD;
                     }
                  }
               }
            }
               
            
            
            
            
         }
      }
      
      
      long answer = 0L;
      
      for(int k = 1; k <= m; k++){
         answer = (answer + dp[n-1][k] + MOD) % MOD;
      }
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
}