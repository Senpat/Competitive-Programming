//Serval and Brain Power
import java.io.*;
import java.util.*;
//tutorial -> split repeats of >= 5 and < 5
public class F1789{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      int n = s.length;
      
      int answer = 0;
      
      //check for 2 repeats
      //starting point of 2nd repeat
      for(int b = 1; b < n; b++){
         int n1 = b;
         int n2 = n-n1;
         int[][] dp = new int[n1][n2];
         
         int maxlen = 0;
         for(int k = 0; k < n1; k++){
            for(int j = 0; j < n2; j++){
               if(k < n1-1){
                  dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j]);
               }
               if(j < n2-1){
                  dp[k][j+1] = Math.max(dp[k][j+1],dp[k][j]);
               }
               
               if(s[k] == s[b+j]){
                  dp[k][j]++;
                  maxlen = Math.max(maxlen,dp[k][j]);
                  if(k < n1-1 && j < n2-1){
                     dp[k+1][j+1] = Math.max(dp[k+1][j+1],dp[k][j]);
                  }
               }
            }
         }
         
         answer = Math.max(answer,maxlen*2);
      }
      
      //check for 3 repeats
      for(int b1 = 1; b1 < n; b1++){
         for(int b2 = b1+1; b2 < n; b2++){
            int n1 = b1;
            int n2 = b2-b1;
            int n3 = n-n1-n2;
            
            int[][][] dp = new int[n1][n2][n3];
            
            int maxlen = 0;
            
            for(int k = 0; k < n1; k++){
               for(int j = 0; j < n2; j++){
                  for(int h = 0; h < n3; h++){
                     if(k < n1-1){
                        dp[k+1][j][h] = Math.max(dp[k+1][j][h],dp[k][j][h]);
                     }
                     if(j < n2-1){
                        dp[k][j+1][h] = Math.max(dp[k][j+1][h],dp[k][j][h]);
                     }
                     if(h < n3-1){
                        dp[k][j][h+1] = Math.max(dp[k][j][h+1],dp[k][j][h]);
                     }  
                     
                     if(s[k] == s[b1+j] && s[k] == s[b2+h]){
                        dp[k][j][h]++;
                        maxlen = Math.max(maxlen,dp[k][j][h]);
                        if(k < n1-1 && j < n2-1 && h < n3-1){
                           dp[k+1][j+1][h+1] = Math.max(dp[k+1][j+1][h+1],dp[k][j][h]);
                        }
                     }
                  }
               }
            }
            
            answer = Math.max(answer,maxlen*3);
         }
      }
      
      //repeats of 4 is covered by repeats of 2 case
      
      //repeats of >= 5
      HashSet<String> checks = new HashSet<String>();
      
      int M = 16;
      for(int block = 0; block < n; block += M){
         int size = Math.min(n-block,M);
         for(int mask = 1; mask < (1 << size); mask++){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++){
               if((mask & (1 << i)) != 0){
                  sb.append(s[block+i]);
               }
            }
            checks.add(sb.toString());
         }
      }
      
      for(String check : checks){
         //see how many times it repeats
         int curn = check.length();
         int i = 0;
         int cur = 0;
         
         for(int k = 0; k < n; k++){
            if(s[k] == check.charAt(i)){
               if(i == curn-1){
                  cur++;
                  i = 0;
                  
               } else {
                  i++;
               }
            }
         }
         
         if(cur >= 2){
            answer = Math.max(answer,cur*curn);
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
      
}