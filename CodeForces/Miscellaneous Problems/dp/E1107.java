//make sure to make new file!
import java.io.*;
import java.util.*;
//DOES NOT WORK AT ALL
public class E1107{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] points = new long[n];
      
      for(int k = 0; k < n; k++){
         points[k] = Long.parseLong(st.nextToken());
      }
      
      long[][][] dp = new long[n][n][n];
      
      for(int l = 0; l < n; l++){
         for(int r = l; r < n; r++){
            
            
            if(!check(array,l,r)) continue;
            dp[l][r][0] = points[r-l+1];
         }
      }
      
      for(int l = 0; l < n; l++){
         for(int r = l; r < n; r++){
            for(int d = 0; d < n; d++){
               
               //check expand to the left
               
               if(l > 0){
                  char c = array[l-1];
                  int i = l;
                  while(i >= 0 && array[i] == c){
                     //check expanding l to i
                     dp[i][r][r-l+1] = Math.max(dp[i][r][r-l+1],dp[l][r][d] + points[i]);
                     i--;
                  }
               }
               
               //check to right
               
               if(r < n-1){
                  char c = array[r+1];
                  int i = r;
                  while(i < n && array[i] == c){
                     //check expanding l to i
                     dp[i][r][r-l+1] = Math.max(dp[i][r][r-l+1],dp[l][r][d] + points[i]);
                     i++;
                  }
               }
            }
         }
      }
      
      long answer = 0L;
      
      for(int l = 0; l < n; l++){
         for(int r = l; r < n; r++){
            for(int d = 0; d < n; d++){
               answer = Math.max(answer,dp[l][r][d]);
            }
         }
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static boolean check(char[] array, int l, int r){
      char c = array[l];
      
      for(int k = l+1; k <= r; k++){
         if(array[k] != c) return false;
      }
      
      return true;
   }
   
      
}