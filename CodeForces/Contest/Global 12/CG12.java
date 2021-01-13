//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG12{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         char[][] board = new char[n][n];
         
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < n; j++){
               board[k][j] = s.charAt(j);
            }
         }
         
         if(n <= 2){
            for(int k = 0; k < n; k++){
               out.println(new String(board[k]));
            }
            continue;
         }
         
         int[] array = new int[2*n-1];
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(board[k][j] != '.'){
                  array[k+j]++;
               }
            }
         }
         
         int[] dp = new int[2*n-1];
         int[] parent = new int[2*n-1];
         Arrays.fill(parent,-1);
         
         for(int k = 0; k < 2*n-1; k++){
            dp[k] = array[k];
            
            if(k >= 3){
               int min = Integer.MAX_VALUE;
               int index = -1;
               for(int j = 1; j <= 3; j++){
                  if(k-j >= 0){
                     if(dp[k-j] < min){
                        index = k-j;
                        min = dp[k-j];
                     }
                  }
               }
               if(index != -1){
                  dp[k] += min;
                  parent[k] = index;
               }
            }
         }
         
         int minval = Integer.MAX_VALUE;
         int start = -1;
         
         for(int j = 1; j <= 3; j++){
            if(2*n-1-j >= 0 && dp[2*n-1-j] < minval){
               minval = dp[2*n-1-j];
               start = 2*n-1-j;
            }
         }
         
         //hopefully minval is < floor(n/3)!
         
         HashSet<Integer> adds = new HashSet<Integer>();
         int i = start;
         
         while(i != -1){
            adds.add(i);
            i = parent[i];
         }
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(adds.contains(k+j)){
                  if(board[k][j] == 'X') board[k][j] = 'O';
                  else if(board[k][j] == 'O') board[k][j] = 'X';
               }
            }
         }
         
         for(int k = 0; k < n; k++){
            out.println(new String(board[k]));
         }
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}